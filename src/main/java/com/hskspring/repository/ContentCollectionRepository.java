package com.hskspring.repository;

import com.hskspring.model.Content;
import org.springframework.stereotype.Repository;
import com.hskspring.model.Status;
import com.hskspring.model.Type;
import jakarta.annotation.PostConstruct;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentCollectionRepository {

    private final List<Content> contentList = new ArrayList<>();

    public ContentCollectionRepository() {
    }

    // find all method creation
    public List<Content> findAll() {
        return contentList;
    }

    // findById method creation
    public Optional<Content> findById(Integer id) {
        return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
    }

    // add a content to list method creation
    public void save(Content content) {
        contentList.removeIf(c -> c.id().equals(content.id()));//when we update, it will remove the existing id
        contentList.add(content);// and add new content with the same id
    }

    @PostConstruct
    private void init() {
        Content c = new Content(1,
                "My First Blog Post",
                "My first blog post",
                Status.IDEA,
                Type.ARTICLE,
                LocalDateTime.now(),
                null,
                "");
        contentList.add(c);
    }

    // existById() to check if content's id exists
    public boolean existById(Integer id) {
        return contentList.stream().filter(c -> c.id().equals(id)).count() == 1;
    }

    public void delete(Integer id) {
        contentList.removeIf(c -> c.id().equals(id));
    }
}
