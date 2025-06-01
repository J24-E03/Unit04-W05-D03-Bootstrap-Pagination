package com.example.booklist.service;

import com.example.booklist.exception.ResourceNotFound;
import com.example.booklist.model.Publisher;
import com.example.booklist.repository.PublisherRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherService {
    private final PublisherRepository publisherRepository;

    public Publisher findAuthorById(Long id) {
        return publisherRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Publisher not found"));
    }

    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    public Publisher save(@Valid Publisher publisher) {
        return publisherRepository.save(publisher);
    }


    public void updatePublisher(Publisher publisher) {
        publisherRepository.save(publisher);
    }
}
