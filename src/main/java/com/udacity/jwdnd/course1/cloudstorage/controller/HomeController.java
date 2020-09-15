package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final UserService userService;
    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialService credentialService;
    private final EncryptionService encryptionService;

    public HomeController(UserService userService, FileService fileService, NoteService noteService, CredentialService credentialService, EncryptionService encryptionService) {
        this.userService = userService;
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @GetMapping()
    public String getHomePage(Authentication authentication, Model model, HttpSession session) {
        User user = userService.getUser(authentication.getName());
        session.setAttribute("user", user);
        int userId = user.getUserId();
        model.addAttribute("files", fileService.getAllFiles(userId));
        model.addAttribute("notes", noteService.getAllNotes(userId));
        model.addAttribute("credentials", credentialService.getAllCredentials(userId));
        return "home";
    }

    @PostMapping("/file-upload")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile fileUpload,
                             Authentication authentication, Model model) throws IOException {
        String uploadError = null;
        User user = userService.getUser(authentication.getName());
        if (fileUpload.isEmpty()) {
            uploadError = "Please Select a File to Upload";
        }
        if (uploadError == null){
            Set<String> allFileName = fileService.getAllFileName(user.getUserId());
            if (allFileName.contains(fileUpload.getOriginalFilename())) {
                uploadError = "Cannot Upload File With Same File Name";
            }
        }
        if (uploadError == null) {
            Integer userId = user.getUserId();
            fileService.uploadFile(fileUpload, userId);
            model.addAttribute("files", fileService.getAllFiles(userId));
        }
        if (uploadError == null) {
            model.addAttribute("Success", "Your File Has Been Uploaded Successfully!");
        } else {
            model.addAttribute("Error", uploadError);
        }
        return "result";
    }

    @GetMapping("/file/view")
    public ResponseEntity<byte[]> viewFile(@RequestParam int fileId) {
        File file = fileService.getFile(fileId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\""
                        + file.getFileName() + "\"")
                .body(file.getFileData());
    }

    @GetMapping("/file/delete/{fileId}")
    public String deleteFile(@PathVariable int fileId, Model model) {
        fileService.deleteFile(fileId);
        model.addAttribute("Success", "Successfully Delete Your File!");
        return "result";
    }

    @PostMapping("/note-new")
    public String addOrEditNote(@RequestParam Integer noteId, @RequestParam String noteTitle, @RequestParam String noteDescription, Authentication authentication, Model model) {
        String username = authentication.getName();
        int userId = userService.getUser(username).getUserId();
        Note note = new Note(noteId, noteTitle, noteDescription, userId);

        if (noteId != null) {
            try {
                noteService.editNote(note);
                model.addAttribute("Success", "Successfully Updated Your Note!");
                return "result";
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("Error", "Something went wrong with the note update. Please try again!");
                return "result";
            }
        }
        else {
            try {
                noteService.createNote(note);
                model.addAttribute("Success", "You Successfully Created A Note!");
                return "result";
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("Error", "Failed to Create Your Note. Please try again!");
                return "result";
            }
        }
    }

    @GetMapping("/note/delete/{noteId}")
    public String deleteNote(@PathVariable int noteId, Model model) {
        try {
            noteService.deleteNote(noteId);
            model.addAttribute("Success", "Your note was deleted successful.");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("Error", "Something went wrong with the note delete. Please try again!");
        }
        return "result";
    }

    @PostMapping("/credential-new")
    public String addOrEditCredential(@RequestParam Integer credentialId, @RequestParam String url, @RequestParam String username, @RequestParam String password, Authentication authentication, Model model) {
        String name = authentication.getName();
        int userId = userService.getUser(name).getUserId();
        String key = encryptionService.getSecureKey();
        String encryptPassword = encryptionService.encryptValue(password, key);
        Credential credential = new Credential(credentialId, url, username, key, encryptPassword, userId);

        if (credentialId != null) {
            try {
                credentialService.editCredential(credential);
                model.addAttribute("Success", "Successfully Updated Your Credential!");
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("Error", "Something went wrong with the credential update. Please try again!");
            }
        }
        else {
            try {
                credentialService.createCredential(credential);
                model.addAttribute("Success", "You Successfully Created A Credential!");
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("Error", "Failed to Create Your Credential. Please try again!");
            }
        }
        return "result";
    }

    @GetMapping("/credential/delete/{credentialId}")
    public String deleteCredential(@PathVariable int credentialId, Model model) {
        try {
            credentialService.deleteCredential(credentialId);
            model.addAttribute("Success", "Your credential was deleted successful.");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("Error", "Something went wrong with the credential delete. Please try again!");
        }
        return "result";
    }

    @GetMapping("/getDecryptedCredential")
    @ResponseBody
    public Map<String, String> getDecryptedCredential(@RequestParam Integer credentialId) {
        Map<String, String> map = new HashMap<>();
        Credential credential = credentialService.getCredential(credentialId);
        String password = credential.getPassword();
        String key = credential.getKey();
        map.put("decryptedPassword", encryptionService.decryptValue(password, key));
        return map;
    }
}
