package com.fc.service;

import com.fc.entity.TbMusic;

import java.util.List;

public interface TbMusicService {
     List<TbMusic> findAll();

     TbMusic nextSong(Integer musicId);


     TbMusic findById(Integer musicId);
     

     TbMusic prevSong(Integer musicId);

     List<TbMusic> search(String keyword);
}
