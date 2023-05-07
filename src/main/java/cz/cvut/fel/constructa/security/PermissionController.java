package cz.cvut.fel.constructa.security;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * The type Permission controller.
 */
@CrossOrigin
@RestController
@RequiredArgsConstructor
@Tag(name = "Permissions")
public class PermissionController {
    /**
     * The Authentication facade.
     */
    private final AuthenticationFacade authenticationFacade;

    /**
     * Gets permission.
     *
     * @return the permission
     * @throws JSONException         the json exception
     */
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(value="/api/v1/permissions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getPermission() throws JSONException {

        Collection<? extends GrantedAuthority> authorities = authenticationFacade.getAuthentication().getAuthorities();
        String permission = authorities.toArray()[0].toString();
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("permission", permission);

        return ResponseEntity.ok().body(jsonObject.toString());
    }
}
