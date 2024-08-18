package org.news.newsapiproject.model;

import lombok.Getter;
import lombok.Setter;
import org.news.newsapiproject.entity.Source;

import java.util.List;

@Setter
@Getter
public class SourcesResponse {
    String status;
    List<Source> sources;
}
