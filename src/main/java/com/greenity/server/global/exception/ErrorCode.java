package com.greenity.server.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    /*
     * 204 NO CONTENT
     */

    /*
     * 400 BAD_REQUEST: 잘못된 요청
     */


    /*
     * 401 UNAUTHORIZED
     */

    /*
     * 403 FORBIDDEN: 권한 없음
     */
    INSUFFICIENT_ACTIVITY(HttpStatus.FORBIDDEN, "활동 횟수가 부족하여 요청을 처리할 수 없습니다."),



    /*
     * 404 NOT_FOUND: 리소스를 찾을 수 없음
     */
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "회원 정보를 찾을 수 없습니다."),
    LOCATION_NOT_FOUND(HttpStatus.NOT_FOUND, "위치 정보를 찾을 수 없습니다.");


    /*
     * 405 METHOD_NOT_ALLOWED: 허용되지 않은 Request Method 호출
     */

    /*
     * 409 CONFLICT: 사용자의 요청이 서버의 상태와 충돌
     */

    /*
     * 500 INTERNAL_SERVER_ERROR
     */

    private final HttpStatus status;
    private final String message;



}
