package com.dali186.template_lab.practice_tdd.service;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ==== 요구사항 ====
 * 1. 계산기 프로그램
 * 요구사항:
 *
 * 덧셈, 뺄셈, 곱셈, 나눗셈 기능을 제공한다.
 * 두 개의 숫자를 입력받아 연산 결과를 반환한다.
 * 나눗셈 연산 시 0으로 나누면 IllegalArgumentException을 발생시킨다.
 * 연산자는 +, -, *, / 문자열로 전달받는다.
 * 테스트 케이스 예시:
 * ✅ 2 + 3 → 5
 * ✅ 5 - 2 → 3
 * ✅ 4 * 3 → 12
 * ✅ 6 / 2 → 3
 * ✅ 6 / 0 → 예외 발생
 *
 * 2. 문자열 압축 기능
 * 요구사항:
 *
 * 같은 문자가 연속으로 반복되면 문자 + 개수로 압축한다.
 * 단, 1번만 등장한 문자는 그대로 유지한다.
 * 예시:
 * ✅ "aaabbc" → "a3b2c"
 * ✅ "abcd" → "abcd"
 * ✅ "aabbccaa" → "a2b2c2a2"
 *
 * 3. 회원 비밀번호 검증 기능
 * 요구사항:
 *
 * 비밀번호는 8자 이상이어야 한다.
 * 최소 1개 이상의 숫자를 포함해야 한다.
 * 최소 1개 이상의 대문자를 포함해야 한다.
 * 최소 1개 이상의 특수 문자(!@#$%^&*)를 포함해야 한다.
 * 테스트 케이스 예시:
 * ✅ "Password1!" → 검증 성공
 * ✅ "pass" → 검증 실패 (8자 미만)
 * ✅ "password123" → 검증 실패 (대문자 없음)
 * ✅ "PASSWORD123" → 검증 실패 (특수 문자 없음)
 *
 * 4. 쇼핑몰 할인 시스템
 * 요구사항:
 *
 * 기본 상품 가격에서 조건에 따라 할인율을 적용한다.
 * 일반 고객: 할인 없음
 * VIP 고객: 10% 할인
 * 첫 구매 고객: 5% 할인
 * 테스트 케이스 예시:
 * ✅ 일반 고객, 10000원 → 10000원
 * ✅ VIP 고객, 10000원 → 9000원
 * ✅ 첫 구매 고객, 10000원 → 9500원
 *
 * 5. 은행 계좌 기능
 * 요구사항:
 *
 * 계좌 생성 시 초기 잔액을 설정할 수 있다.
 * 입금 기능이 있다.
 * 출금 기능이 있다. (잔액보다 많이 출금하면 오류 발생)
 * 잔액 조회 기능이 있다.
 * 테스트 케이스 예시:
 * ✅ 10000원으로 계좌 생성 → 잔액 10000원
 * ✅ 5000원 입금 → 잔액 15000원
 * ✅ 3000원 출금 → 잔액 12000원
 * ✅ 20000원 출금 → 오류 발생
 */

class IndependentServiceTest {

}