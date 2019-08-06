package zombie.crawler.dto;

public class CrawlerSourceDTO {
    private Long id;
    private String url;
    private String linkSelector;
    private int linkLimit;
    private String titleSelector;
    private String descriptionSelector;
    private String contentSelector;
    private String authorSelector;
    private String thumbnailSelector;
    private Long categoryId;
    private int status;

    public CrawlerSourceDTO() {
    }

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

    public String getLinkSelector() {
        return linkSelector;
    }

    public void setLinkSelector(String linkSelector) {
        this.linkSelector = linkSelector;
    }

    public int getLinkLimit() {
        return linkLimit;
    }

    public void setLinkLimit(int linkLimit) {
        this.linkLimit = linkLimit;
    }

    public String getTitleSelector() {
        return titleSelector;
    }

    public void setTitleSelector(String titleSelector) {
        this.titleSelector = titleSelector;
    }

    public String getDescriptionSelector() {
        return descriptionSelector;
    }

    public void setDescriptionSelector(String descriptionSelector) {
        this.descriptionSelector = descriptionSelector;
    }

    public String getContentSelector() {
        return contentSelector;
    }

    public void setContentSelector(String contentSelector) {
        this.contentSelector = contentSelector;
    }

    public String getAuthorSelector() {
        return authorSelector;
    }

    public void setAuthorSelector(String authorSelector) {
        this.authorSelector = authorSelector;
    }

    public String getThumbnailSelector() {
        return thumbnailSelector;
    }

    public void setThumbnailSelector(String thumbnailSelector) {
        this.thumbnailSelector = thumbnailSelector;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
