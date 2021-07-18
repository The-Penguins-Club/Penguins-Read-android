package club.thepenguins.android.data;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("taxonomy")
    @Expose
    private String taxonomy;
    @SerializedName("parent")
    @Expose
    private Integer parent;
    @SerializedName("meta")
    @Expose
    private List<Object> meta = null;
    @SerializedName("yoast_head")
    @Expose
    private String yoastHead;
    @SerializedName("yoast_head_json")
    @Expose
    private YoastHeadJson yoastHeadJson;
    @SerializedName("_links")
    @Expose
    private Links links;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(String taxonomy) {
        this.taxonomy = taxonomy;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public List<Object> getMeta() {
        return meta;
    }

    public void setMeta(List<Object> meta) {
        this.meta = meta;
    }

    public String getYoastHead() {
        return yoastHead;
    }

    public void setYoastHead(String yoastHead) {
        this.yoastHead = yoastHead;
    }

    public YoastHeadJson getYoastHeadJson() {
        return yoastHeadJson;
    }

    public void setYoastHeadJson(YoastHeadJson yoastHeadJson) {
        this.yoastHeadJson = yoastHeadJson;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }


    public class About {

        @SerializedName("href")
        @Expose
        private String href;

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

    }

    public class Breadcrumb {

        @SerializedName("@id")
        @Expose
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    }


    public class Collection {

        @SerializedName("href")
        @Expose
        private String href;

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

    }

    public class Cury {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("href")
        @Expose
        private String href;
        @SerializedName("templated")
        @Expose
        private Boolean templated;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public Boolean getTemplated() {
            return templated;
        }

        public void setTemplated(Boolean templated) {
            this.templated = templated;
        }

    }

    public class Graph {

        @SerializedName("@type")
        @Expose
        private String type;
        @SerializedName("@id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("sameAs")
        @Expose
        private List<String> sameAs = null;
        @SerializedName("logo")
        @Expose
        private Logo logo;
        @SerializedName("image")
        @Expose
        private Image image;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("publisher")
        @Expose
        private Publisher publisher;
        @SerializedName("potentialAction")
        @Expose
        private List<PotentialAction> potentialAction = null;
        @SerializedName("inLanguage")
        @Expose
        private String inLanguage;
        @SerializedName("isPartOf")
        @Expose
        private IsPartOf isPartOf;
        @SerializedName("breadcrumb")
        @Expose
        private Breadcrumb breadcrumb;
        @SerializedName("itemListElement")
        @Expose
        private List<ItemListElement> itemListElement = null;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<String> getSameAs() {
            return sameAs;
        }

        public void setSameAs(List<String> sameAs) {
            this.sameAs = sameAs;
        }

        public Logo getLogo() {
            return logo;
        }

        public void setLogo(Logo logo) {
            this.logo = logo;
        }

        public Image getImage() {
            return image;
        }

        public void setImage(Image image) {
            this.image = image;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Publisher getPublisher() {
            return publisher;
        }

        public void setPublisher(Publisher publisher) {
            this.publisher = publisher;
        }

        public List<PotentialAction> getPotentialAction() {
            return potentialAction;
        }

        public void setPotentialAction(List<PotentialAction> potentialAction) {
            this.potentialAction = potentialAction;
        }

        public String getInLanguage() {
            return inLanguage;
        }

        public void setInLanguage(String inLanguage) {
            this.inLanguage = inLanguage;
        }

        public IsPartOf getIsPartOf() {
            return isPartOf;
        }

        public void setIsPartOf(IsPartOf isPartOf) {
            this.isPartOf = isPartOf;
        }

        public Breadcrumb getBreadcrumb() {
            return breadcrumb;
        }

        public void setBreadcrumb(Breadcrumb breadcrumb) {
            this.breadcrumb = breadcrumb;
        }

        public List<ItemListElement> getItemListElement() {
            return itemListElement;
        }

        public void setItemListElement(List<ItemListElement> itemListElement) {
            this.itemListElement = itemListElement;
        }

    }

    public class Image {

        @SerializedName("@id")
        @Expose
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    }

    public class IsPartOf {

        @SerializedName("@id")
        @Expose
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    }

    public class ItemListElement {

        @SerializedName("@type")
        @Expose
        private String type;
        @SerializedName("position")
        @Expose
        private Integer position;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("item")
        @Expose
        private String item;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Integer getPosition() {
            return position;
        }

        public void setPosition(Integer position) {
            this.position = position;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

    }

    public class Links {

        @SerializedName("self")
        @Expose
        private List<Self> self = null;
        @SerializedName("collection")
        @Expose
        private List<Collection> collection = null;
        @SerializedName("about")
        @Expose
        private List<About> about = null;
        @SerializedName("up")
        @Expose
        private List<Up> up = null;
        @SerializedName("wp:post_type")
        @Expose
        private List<WpPostType> wpPostType = null;
        @SerializedName("curies")
        @Expose
        private List<Cury> curies = null;

        public List<Self> getSelf() {
            return self;
        }

        public void setSelf(List<Self> self) {
            this.self = self;
        }

        public List<Collection> getCollection() {
            return collection;
        }

        public void setCollection(List<Collection> collection) {
            this.collection = collection;
        }

        public List<About> getAbout() {
            return about;
        }

        public void setAbout(List<About> about) {
            this.about = about;
        }

        public List<Up> getUp() {
            return up;
        }

        public void setUp(List<Up> up) {
            this.up = up;
        }

        public List<WpPostType> getWpPostType() {
            return wpPostType;
        }

        public void setWpPostType(List<WpPostType> wpPostType) {
            this.wpPostType = wpPostType;
        }

        public List<Cury> getCuries() {
            return curies;
        }

        public void setCuries(List<Cury> curies) {
            this.curies = curies;
        }

    }

    public class Logo {

        @SerializedName("@type")
        @Expose
        private String type;
        @SerializedName("@id")
        @Expose
        private String id;
        @SerializedName("inLanguage")
        @Expose
        private String inLanguage;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("contentUrl")
        @Expose
        private String contentUrl;
        @SerializedName("width")
        @Expose
        private Integer width;
        @SerializedName("height")
        @Expose
        private Integer height;
        @SerializedName("caption")
        @Expose
        private String caption;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getInLanguage() {
            return inLanguage;
        }

        public void setInLanguage(String inLanguage) {
            this.inLanguage = inLanguage;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getContentUrl() {
            return contentUrl;
        }

        public void setContentUrl(String contentUrl) {
            this.contentUrl = contentUrl;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

        public String getCaption() {
            return caption;
        }

        public void setCaption(String caption) {
            this.caption = caption;
        }

    }

    public class PotentialAction {

        @SerializedName("@type")
        @Expose
        private String type;
        @SerializedName("target")
        @Expose
        private List<String> target = null;
        @SerializedName("query-input")
        @Expose
        private String queryInput;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<String> getTarget() {
            return target;
        }

        public void setTarget(List<String> target) {
            this.target = target;
        }

        public String getQueryInput() {
            return queryInput;
        }

        public void setQueryInput(String queryInput) {
            this.queryInput = queryInput;
        }

    }

    public class Publisher {

        @SerializedName("@id")
        @Expose
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    }

    public class Robots {

        @SerializedName("index")
        @Expose
        private String index;
        @SerializedName("follow")
        @Expose
        private String follow;
        @SerializedName("max-snippet")
        @Expose
        private String maxSnippet;
        @SerializedName("max-image-preview")
        @Expose
        private String maxImagePreview;
        @SerializedName("max-video-preview")
        @Expose
        private String maxVideoPreview;

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public String getFollow() {
            return follow;
        }

        public void setFollow(String follow) {
            this.follow = follow;
        }

        public String getMaxSnippet() {
            return maxSnippet;
        }

        public void setMaxSnippet(String maxSnippet) {
            this.maxSnippet = maxSnippet;
        }

        public String getMaxImagePreview() {
            return maxImagePreview;
        }

        public void setMaxImagePreview(String maxImagePreview) {
            this.maxImagePreview = maxImagePreview;
        }

        public String getMaxVideoPreview() {
            return maxVideoPreview;
        }

        public void setMaxVideoPreview(String maxVideoPreview) {
            this.maxVideoPreview = maxVideoPreview;
        }

    }

    public class Schema {

        @SerializedName("@context")
        @Expose
        private String context;
        @SerializedName("@graph")
        @Expose
        private List<Graph> graph = null;

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public List<Graph> getGraph() {
            return graph;
        }

        public void setGraph(List<Graph> graph) {
            this.graph = graph;
        }

    }

    public class Self {

        @SerializedName("href")
        @Expose
        private String href;

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

    }

    public class Up {

        @SerializedName("embeddable")
        @Expose
        private Boolean embeddable;
        @SerializedName("href")
        @Expose
        private String href;

        public Boolean getEmbeddable() {
            return embeddable;
        }

        public void setEmbeddable(Boolean embeddable) {
            this.embeddable = embeddable;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

    }

    public class WpPostType {

        @SerializedName("href")
        @Expose
        private String href;

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

    }

    public class YoastHeadJson {

        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("robots")
        @Expose
        private Robots robots;
        @SerializedName("canonical")
        @Expose
        private String canonical;
        @SerializedName("og_locale")
        @Expose
        private String ogLocale;
        @SerializedName("og_type")
        @Expose
        private String ogType;
        @SerializedName("og_title")
        @Expose
        private String ogTitle;
        @SerializedName("og_url")
        @Expose
        private String ogUrl;
        @SerializedName("og_site_name")
        @Expose
        private String ogSiteName;
        @SerializedName("twitter_card")
        @Expose
        private String twitterCard;
        @SerializedName("schema")
        @Expose
        private Schema schema;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Robots getRobots() {
            return robots;
        }

        public void setRobots(Robots robots) {
            this.robots = robots;
        }

        public String getCanonical() {
            return canonical;
        }

        public void setCanonical(String canonical) {
            this.canonical = canonical;
        }

        public String getOgLocale() {
            return ogLocale;
        }

        public void setOgLocale(String ogLocale) {
            this.ogLocale = ogLocale;
        }

        public String getOgType() {
            return ogType;
        }

        public void setOgType(String ogType) {
            this.ogType = ogType;
        }

        public String getOgTitle() {
            return ogTitle;
        }

        public void setOgTitle(String ogTitle) {
            this.ogTitle = ogTitle;
        }

        public String getOgUrl() {
            return ogUrl;
        }

        public void setOgUrl(String ogUrl) {
            this.ogUrl = ogUrl;
        }

        public String getOgSiteName() {
            return ogSiteName;
        }

        public void setOgSiteName(String ogSiteName) {
            this.ogSiteName = ogSiteName;
        }

        public String getTwitterCard() {
            return twitterCard;
        }

        public void setTwitterCard(String twitterCard) {
            this.twitterCard = twitterCard;
        }

        public Schema getSchema() {
            return schema;
        }

        public void setSchema(Schema schema) {
            this.schema = schema;
        }

    }
}