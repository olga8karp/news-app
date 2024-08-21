package org.news.newsapiproject.service;

import org.news.newsapiproject.entity.Article;
import org.news.newsapiproject.entity.Comment;
import org.news.newsapiproject.entity.User;

import java.util.List;

public interface ArticleDatabaseService {
    public void addCommentToArticle(String url, String comment);
    Article addLikes(String url, User user);
    Article getArticleByUrl(String url);
    List<Comment> loadCommentsByArticleUrl(String url);
}
