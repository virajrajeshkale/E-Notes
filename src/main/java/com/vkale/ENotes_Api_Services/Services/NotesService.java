package com.vkale.ENotes_Api_Services.Services;

import com.vkale.ENotes_Api_Services.Dto.NotesDto;

import java.util.List;

public interface NotesService {
    public Boolean saveNotes(NotesDto notesDto) throws Exception;

    public List<NotesDto> getAllNotes();
}
