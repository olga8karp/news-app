package org.news.newsapiproject.service.impl;

import org.news.newsapiproject.entity.Article;
import org.news.newsapiproject.entity.Comment;
import org.news.newsapiproject.entity.User;
import org.news.newsapiproject.service.ArticleDatabaseService;

import java.util.List;

public class ArticleDatabaseServiceImpl implements ArticleDatabaseService {

    @Override
    public void addCommentToArticle(String url, String comment) {

    }

    @Override
    public Article addLikes(String url, User user) {
        return null;
    }

    @Override
    public Article getArticleByUrl(String url) {
        return null;
    }

    @Override
    public List<Comment> loadCommentsByArticleUrl(String url) {
        return List.of();
    }
}
