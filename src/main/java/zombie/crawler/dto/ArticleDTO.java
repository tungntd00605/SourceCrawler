package zombie.crawler.dto;

public class ArticleDTO {
    private Long id;
    private String url;
    private String title;
    private CategoryDTO category;
    private String description;
    private String content;
    private String thumbnail;
    private String author;
    private CrawlerSourceDTO crawlerSource;
    private int status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public CrawlerSourceDTO getCrawlerSource() {
        return crawlerSource;
    }

    public void setCrawlerSource(CrawlerSourceDTO crawlerSource) {
        this.crawlerSource = crawlerSource;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public static final class Builder {
        private Long id;
        private String url;
        private String title;
        private CategoryDTO category;
        private String description;
        private String content;
        private String thumbnail;
        private String author;
        private CrawlerSourceDTO crawlerSource;
        private int status;

        private Builder() {
        }

        public static Builder anArticleDTO() {
            return new Builder();
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withCategory(CategoryDTO category) {
            this.category = category;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withContent(String content) {
            this.content = content;
            return this;
        }

        public Builder withThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        public Builder withAuthor(String author) {
            this.author = author;
            return this;
        }

        public Builder withCrawlerSource(CrawlerSourceDTO crawlerSource) {
            this.crawlerSource = crawlerSource;
            return this;
        }

        public Builder withStatus(int status) {
            this.status = status;
            return this;
        }

        public ArticleDTO build() {
            ArticleDTO articleDTO = new ArticleDTO();
            articleDTO.setId(id);
            articleDTO.setUrl(url);
            articleDTO.setTitle(title);
            articleDTO.setCategory(category);
            articleDTO.setDescription(description);
            articleDTO.setContent(content);
            articleDTO.setThumbnail(thumbnail);
            articleDTO.setAuthor(author);
            articleDTO.setCrawlerSource(crawlerSource);
            articleDTO.setStatus(status);
            return articleDTO;
        }
    }
}
