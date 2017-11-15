package com.kcview.service;

import java.util.List;

import com.kcview.entity.Club;

public interface IClubService {
     List<Club> getAllClubs();
     Club getClubById(int clubId);
     boolean addClub(Club club);
     void updateClub(Club club);
     void deleteClub(int clubId);
}
