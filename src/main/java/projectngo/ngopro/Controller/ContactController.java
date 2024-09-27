package projectngo.ngopro.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projectngo.ngopro.Entity.Contact;
import projectngo.ngopro.Service.ContactService;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @PostMapping("/save")
    public Contact saveContact(@RequestBody Contact contact) {
        return contactService.saveContact(contact);
    }
}
