package ImageHoster.repository;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;


//The annotation is a special type of @Component annotation which describes that the class defines a data repository
@Repository
public class CommentRepository {
    
    //Get an instance of EntityManagerFactory from persistence unit with name as 'imageHoster'
    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;
    
    /**
     * Adds a comment to the image and stores it in the database
     *
     * @param image       Image object
     * @param user        Current user
     * @param commentText Comment text to be added
     * @return the newly added Comment
     */
    public Comment addComment(Image image, User user, String commentText) {
        List<Comment> comments = image.getComments();
        Comment newComment = new Comment();
        newComment.setImage(image);
        newComment.setText(commentText);
        newComment.setUser(user);
        newComment.setCreatedDate(LocalDate.now());
        if (CollectionUtils.isEmpty(comments)) {
            comments = new ArrayList<>();
        }
        comments.add(newComment);
        image.setComments(comments);
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(newComment);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return newComment;
    }
    
}
