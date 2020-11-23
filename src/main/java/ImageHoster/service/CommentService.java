package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    
    @Autowired
    private CommentRepository commentRepository;
    
    /**
     * Adds a comment to the image
     *
     * @param image   Image object
     * @param user    Current user
     * @param comment Comment text to be added
     * @return the newly added Comment
     */
    public Comment addComment(Image image, User user, String comment) {
        return commentRepository.addComment(image, user, comment);
    }
    
}
