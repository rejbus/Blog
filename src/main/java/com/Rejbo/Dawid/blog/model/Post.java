package com.Rejbo.Dawid.blog.model;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



    @Entity


    public class Post {

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Date getAdded() {
            return added;
        }

        public void setAdded(Date added) {
            this.added = added;
        }

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private String title;

        private String content;

        @Temporal(TemporalType.TIMESTAMP)
        private Date added = new Date();

        public Post(String title, String content) {
            this.title = title;
            this.content = content;
        }

        public Post() {

        }

        @OneToMany(cascade = CascadeType.PERSIST)
        @JoinColumn(name = "postId")
        List<PostComment> comments = new ArrayList<>();

        public void addComment(PostComment postComment) {
            comments.add(postComment);
            postComment.setPost(this);
        }

        public void removeComment(PostComment postComment) {
            comments.remove(postComment);
            postComment.setPost(null);
        }

        public List<PostComment> getComments() {
            return comments;
        }

        public void setComments(List<PostComment> comments) {
            this.comments = comments;
        }
    }

