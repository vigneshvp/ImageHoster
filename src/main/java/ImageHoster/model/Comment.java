package ImageHoster.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


//@Entity annotation specifies that the corresponding class is a JPA entity
@Entity
//@Table annotation provides more options to customize the mapping.
//Here the name of the table to be created in the database is explicitly mentioned as 'comments'.
// Hence the table named 'comments' will be created in the database with all the columns mapped to all the attributes in 'Comment' class
@Table(name = "comments")
public class Comment {
    
    //@Id annotation specifies that the corresponding attribute is a primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column annotation specifies that the attribute will be mapped to the column in the database.
    //Here the column name is explicitly mentioned as 'id'
    @Column(name = "id")
    private Integer id;
    
    //Comment is stored as text instead of varchar to support more than 255 characters.
    @Column(name = "text", columnDefinition = "TEXT")
    private String text;
    
    //Created Date of the comment
    @Column(name = "created_date")
    private LocalDate createdDate;
    
    //The 'comments' table is mapped to 'users' table with Many:One mapping
    //One comment can have only one user (owner) but one user can post multiple comments
    //FetchType is EAGER
    @ManyToOne(fetch = FetchType.EAGER)
    //Below annotation indicates that the name of the column in 'comments' table referring the primary key in 'users' table will be 'user_id'
    @JoinColumn(name = "user_id")
    private User user;
    
    //The 'comments' table is mapped to 'image' table with Many:One mapping
    //One comment can correspond to only one image (owner) but one image can have multiple comments
    //FetchType is EAGER
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;
    
    public Comment(Integer id, String text, User user, Image image) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.image = image;
    }
    
    public Comment(Integer id, String text, LocalDate createdDate, User user,
        Image image) {
        this.id = id;
        this.text = text;
        this.createdDate = createdDate;
        this.user = user;
        this.image = image;
    }
    
    public Comment() {
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public LocalDate getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public Image getImage() {
        return image;
    }
    
    public void setImage(Image image) {
        this.image = image;
    }
    
    
}
