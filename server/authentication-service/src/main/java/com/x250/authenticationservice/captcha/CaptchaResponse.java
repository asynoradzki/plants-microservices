package com.x250.authenticationservice.captcha;

import lombok.Data;

@Data
public class CaptchaResponse {

    private boolean success;
    private String challenge_ts;
    private String hostname;
}
