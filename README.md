# java-convenience-store-precourse

## 구현 기능 목록
- [x] ```프로모션 파일 읽어오기```
  - [x] 프로모션 리스트를 만들때부터 프로모션 진행중인 것만 필터링
- [x] ```상품 파일 읽어오기```
  - [x] 재고 없음 표시하기
  - [x] 프로모션이 진행중인 상품 or 일반 상품만 필터링
- [x] ```인사말 및 보유중인 상품 출력```
- [x] ```구매할 상품명 및 수량 입력 받기```
- [x] ```입력값 파싱 및 예외 처리```
  - [x] 구매할 상품 및 수량 형식 예외 처리
  - [x] 존재하지 않는 상품 예외 처리
  - [x] 구매 수량이 재고 수량 초과 예외 처리
  - [x] 잘못된 값을 가진 수량 예외 처리
- [ ] ```정상적인 프로모션 할인 적용 가능 여부 판단하기```
  - [ ] 구매할 상품이 현재 프로모션 기간 내 포함되어 있는지 처리
  - [ ] 구매 수량에 따른 프로모션 재고 부족 여부 판단
  - [ ] 프로모션을 위한 필요 수량 추가 가능 여부 판단
- [ ] ```정상적으로 프로모션 적용이 어려운 경우, 프로모션 할인 적용 여부 안내메시지 출력하기``` 
  - [ ] 프로모션 재고 부족 안내
  - [ ] 부족한 프로모션 혜택 상품 추가 안내 
- [ ] ```3번 안내메시지의 답 입력 받기```
  - [ ] 잘못된 입력 예외 처리
- [ ] ```멤버십 할인 혜택 적용 여부 안내메시지 출력하기```
- [ ] ```5번 안내메시지의 답 입력 받기```
  - [ ] 잘못된 입력 예외 처리
- [ ] ```영수증에 출력할 내용 정리 및 계산하기```
  - [ ] 구매 상품 내역
  - [ ] 증정 상품 내역
  - [ ] 총 구매액
  - [ ] 행사 할인
  - [ ] 멤버십 할인
  - [ ] 내실 돈
- [ ] ```영수증 출력하기``` 
- [ ] ```추가 구매 여부 입력 받기```
  - [ ] 잘못된 입력 예외 처리


### 추가된 요구 사항
1. 입출력을 담당하는 클래스를 별도로 구분 -```InputView```, ```OutputView```

### 구현해야 하는 것
```
구매자의 할인 혜택과 재고 상황을 고려하여 
최종 결제 금액 계산 및 안내하는 결제 시스템
```

1. 사용자가 입력한 상품의 가격과 수량을 기반으로 최종 결제 금액 계산
   - 총구매액은 상품별 가격과 수량을 곱하여 계산하며, 프로모션 및 멤버십 할인 정책을 반영하여 최종 결제 금액 산출
2. 구매 내역과 산출한 금액 정보를 영수증으로 출력
3. 영수증 출력 후 추가 구매를 진행할지, 종료할지 선택 가능
4. 에러 출력 후 해당 부분부터 다시 입력 받기

### 재고 관리
- 각 상품의 재고 수량을 고려하여 결제 가능 여부 확인
- 고객이 상품 구매할 때마다, 결제된 수량만큼 해당 상품의 재고에서 차감
- 시스템은 최신 재고 상태 유지 및 정확한 재고 정보 제공

### 프로모션 할인
- 오늘 날짜가 프로모션 기간 내에 포함된 경우에만 할인 적용
- 프로모션은 N개 구매 시, 1개 무료 증정의 형태로 진행
- 1+1, 2+1 프로모션이 각각 지정된 상품에 적용되며, 동일 상품에 여러 프로모션이 적용되지 않음
- 프로모션 혜택은 프로모션 재고 내에서만 적용 가능
- 프로모션 기간 중이라면, 프로모션 재고를 우선적으로 차감, 프로모션 재고 부족 시 일반 재고 사용
- 프로모션 적용이 가능한 상품에 대해 고객이 해당 수량보다 적게 가져온 경우, 필요한 수량을 추가로 가져오면 혜택을 받을 수 있음을 안내
- 프로모션 재고가 부족하여, 일부 수량을 프로모션 혜택없이 결제해야 하는 경우, 일부 수량에 대해 정가로 결제됨을 안내

### 멤버십 할인
- 멤버십 회원은 프로모션 미적용 금액의 30% 할인 받음
- 포로모션 적용 후 남은 금액에 대해 멤버십 할인을 적용함
- 멤버십 할인의 최대 한도는 8,000원

### 영수증 출력
- 영수증은 고객의 구매 내역 및 할인을 요약하여 출력
- 영수증 항목
  1. 구매 상품 내역: 구매한 상품명, 수량, 가격
  2. 증정 상품 내역: 프로모션에 따라 무료로 제공된 증정 상품의 목록
  3. 금액 정보
     1. 총 구매액 : 구매한 상품의 총 수량과 총 금액
     2. 행사 할인 : 프로모션에 의해 할인된 금액
     3. 멤버십할인 : 멤버십에 의해 추가로 할인된 금액
     4. 내실 돈 : 최종 결제 금액
- 영수증의 구성 요소를 보기 좋게 정렬하여 고객이 쉽게 금액 및 수량을 확인 가능하게 함

### 입출력 요구 사항
- 입력 : 구현에 필요한 상품 목록 및 행사 목록을 파일 입출력을 통해 불러오기
  - products.md, promotions.md
  - 형식만 유지한다면 값 수정 가능
  - 구매할 상품 및 수량 입력받기
    ```
    [콜라-10],[사이다-3] // 대활호, 하이픈, 쉼표로 구분
      ```
  - 프로모션 적용 가능 상품에 대해 고객이 해당 수량보다 적게 가져온 경우, 그 수량만큼 추가 여부 입력받기
    ```
    Y // 일부 수량에 대해 정가 결제
    N // 정가로 결제해야하는 수량만큼 제외 후 결제 진행
    ```
  - 멤버십 할인 적용 여부 입력 받기
    ```
    Y // 멤버십 할인 적용
    N // 멤버십 할인 적용 X
    ```
  - 추가 구매 여부 입력 받기
    ```
    Y : 재고가 업데이트된 상품 목록 환인 후 추가로 구매 진행
    N : 구매 종료
    ```

- 출력
  - 재고가 0개라면 재고 없음 출력
  - 프로모션 적용 가능 상품에 대해 고객이 수량만큼 가져오지 않았을 경우, 혜택에 대한 안내 메시지 출력
  - 프로모션 재고 부족하여, 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우, 일부 수량에 대해 정가 결제 여부 안내 메시지 출력
  - 멤버십 할인 적용 여부 확인 안내 문구 출력
  - 구매 상품 내역, 증정 상품 내역, 금액 정보 출력
  - 추가 구매 여부 확인을 위한 안내 문구 출력
  - 에러
    - 구매할 상품과 수량 형식이 올바르지 않은 경우: ```[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.```
    - 존재하지 않는 상품을 입력한 경우: ```[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.```
    - 구매 수량이 재고 수량을 초과한 경우: ```[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.```
    - 기타 잘못된 입력의 경우: ```[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.```