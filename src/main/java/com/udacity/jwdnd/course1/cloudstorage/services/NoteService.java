package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.PublicKey;
import java.util.List;

@Service
public class NoteService {
    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public void createNote(Note note) {
        noteMapper.insertNote(note);
    }

    public Note getNote(int noteId) {
        return noteMapper.getNoteById(noteId);
    }

    public List<Note> getAllNotes(int userId) {
        return noteMapper.getNoteByUser(userId);
    }

    public void editNote(Note note) {
        noteMapper.updateNote(note);
    }

    public void deleteNote(int noteId) {
        noteMapper.deleteNoteById(noteId);
    }
}
