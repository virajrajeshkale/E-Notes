package com.vkale.ENotes_Api_Services.Services.Impl;

import com.vkale.ENotes_Api_Services.Dto.CategoryDto;
import com.vkale.ENotes_Api_Services.Dto.NotesDto;
import com.vkale.ENotes_Api_Services.Entity.Notes;
import com.vkale.ENotes_Api_Services.Exception.ResourceNotFoundException;
import com.vkale.ENotes_Api_Services.Repository.CategoryRepository;
import com.vkale.ENotes_Api_Services.Repository.NotesRepository;
import com.vkale.ENotes_Api_Services.Services.NotesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class NotesServiceImpl implements NotesService {

    @Autowired
    private NotesRepository notesRepo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CategoryRepository categoryRepo;

    @Override
    public Boolean saveNotes(NotesDto notesDto) throws Exception {

        // category validation
        checkCategoryExist(notesDto.getCategory());

        Notes notes = mapper.map(notesDto, Notes.class);
        Notes saveNotes = notesRepo.save(notes);
        if (!ObjectUtils.isEmpty(saveNotes)) {
            return true;
        }
        return false;
    }

    private void checkCategoryExist(CategoryDto category) throws Exception {
        categoryRepo.findById(category.getId()).orElseThrow(() -> new ResourceNotFoundException("category id invalid"));
    }

    @Override
    public List<NotesDto> getAllNotes() {
        return notesRepo.findAll().stream().map(note -> mapper.map(note, NotesDto.class)).toList();
    }

}
