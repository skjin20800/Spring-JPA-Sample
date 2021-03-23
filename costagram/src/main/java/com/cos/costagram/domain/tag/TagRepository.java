package com.cos.costagram.domain.tag;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.costagram.domain.image.Image;

public interface TagRepository extends JpaRepository<Image, Integer>{

}
