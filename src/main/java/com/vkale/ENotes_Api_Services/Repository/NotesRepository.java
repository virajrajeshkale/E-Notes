package com.vkale.ENotes_Api_Services.Repository;

import com.vkale.ENotes_Api_Services.Entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesRepository extends JpaRepository<Notes, Integer> {
}
