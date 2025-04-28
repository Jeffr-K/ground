package com.meshcraft.groundpoint.core.values

enum class PointActionType {
    REGISTERED,            // 회원 가입 보상
    MATCHED,               // 매칭 성사
    MATCHED_FAILURE,       // 매칭 실패
    EVENT,                 // 이벤트/프로모션
    REFERRAL,              // 추천인 보상
    EXPIRED,               // 만료
    ADJUSTED_ADMIN,        // 관리자 조정
    FROZEN,                // 계정 탈퇴로 인한 동결
    UNFROZEN               // 계정 복구로 인한 동결 해제
}