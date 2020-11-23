package ImageHoster.controller;

import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {
    
    @Autowired
    private CommentService commentService;
    
    @Autowired
    private ImageService imageService;
    
    /**
     * Adds a comment to an existing image and then redirects the image page
     *
     * @param imageId id of the image
     * @param title   title of the image
     * @param comment comment to be added
     * @param session current user session
     * @return redirect url for the corresponding image
     */
    @RequestMapping(value = "/image/{imageId}/{title}/comments", method = RequestMethod.POST)
    public String addCommentToImage(@PathVariable("imageId") int imageId,
        @PathVariable("title") String title, @RequestParam("comment") String comment,
        HttpSession session) {
        User user = (User) session.getAttribute("loggeduser");
        Image image = imageService.getImageByIdAndTitle(imageId, title);
        commentService.addComment(image, user, comment);
        return "redirect:/images/" + imageId + "/" + title;
    }
}