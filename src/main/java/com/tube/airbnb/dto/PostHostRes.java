package com.tube.airbnb.dto;

import com.tube.airbnb.entity.Host;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.parameters.P;

import javax.swing.text.StyledEditorKit;

@Getter
@Setter
@NoArgsConstructor
public class PostHostRes {
    private long hostId;
    private Boolean isVerified;

    public static PostHostRes from(Host host){
        PostHostRes postHostRes = new PostHostRes();
        postHostRes.setHostId(host.getId());
        postHostRes.setIsVerified(host.getIsVerified());
        return postHostRes;
    }
}
