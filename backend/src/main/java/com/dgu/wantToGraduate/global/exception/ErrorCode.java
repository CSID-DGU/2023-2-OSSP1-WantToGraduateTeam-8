package com.dgu.wantToGraduate.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    /* 10* 가계부 */
    MEMO_LIMIT(BAD_REQUEST, 100, "메모는 8개까지 작성 가능합니다."),
    ACCOUNTBOOK_ALREADY_EXISTS(BAD_REQUEST, 101, "가계부가 이미 존재합니다."),
    /* 20* 동네정보 */
    POST_NOT_EXISTS(BAD_REQUEST, 200, "없는 게시글입니다."),
    COMMENT_NOT_EXISTS(BAD_REQUEST, 201, "없는 댓글입니다."),

    /* 50* 스크랩 */

    /* 60* 신고 */
    REPORT_ALREADY_EXISTS(BAD_REQUEST, 600, "이미 신고 처리가 되었습니다."),

    /* 200 NO_CONTENT : 자료를 찾을 수 없음 */
    NO_CONTENT_FOUND(NO_CONTENT, 204, "요청된 자료를 찾을 수 없습니다."),

    /* 400 BAD_REQUEST : 잘못된 요청 */
    INVALID_REFRESH_TOKEN(BAD_REQUEST, 400, "리프레시 토큰이 유효하지 않습니다"),
    MISMATCH_REFRESH_TOKEN(BAD_REQUEST, 400, "리프레시 토큰의 유저 정보가 일치하지 않습니다"),
    CANNOT_FOLLOW_MYSELF(BAD_REQUEST, 400,"자기 자신은 팔로우 할 수 없습니다"),
    INVALID_VALUE(BAD_REQUEST, 400, "잘못된 값입니다." ),


    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    INVALID_AUTH_TOKEN(UNAUTHORIZED,401, "권한 정보가 없는 토큰입니다"),
    UNAUTHORIZED_MEMBER(UNAUTHORIZED,401, "현재 내 계정 정보가 존재하지 않습니다"),

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    USER_NOT_REGISTERED(NOT_FOUND,404, "가입되지 않은 유저입니다"),
    USER_NOT_FOUND(NOT_FOUND,404, "해당 유저 정보를 찾을 수 없습니다"),
    REFRESH_TOKEN_NOT_FOUND(NOT_FOUND, 404,"로그아웃 된 사용자입니다"),
    NOT_FOLLOW(NOT_FOUND,404, "팔로우 중이지 않습니다"),


    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
    DUPLICATE_RESOURCE(CONFLICT,409, "데이터가 이미 존재합니다"),

    //500 INTERNAL SERVER ERROR
    SERVER_ERROR(INTERNAL_SERVER_ERROR, 500, "서버 에러입니다.");


    private final HttpStatus httpStatus;
    private final int errorCode;
    private final String detail;
}