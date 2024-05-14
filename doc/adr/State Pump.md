# State Pump

[`HistoryPage`](../../app/src/main/java/com/github/hemoptysisheart/sample/ui/page/HistoryPage.kt),
[`MazePage`](../../app/src/main/java/com/github/hemoptysisheart/sample/ui/page/MazePage.kt),
[`SelectSizePage`](../../app/src/main/java/com/github/hemoptysisheart/sample/ui/page/SelectSizePage.kt)를 보면 비슷한
형태의 `Scaffold` 레이아웃이 반복적으로 나타난다.

앱의 전체적인 시각적, 사용법을 통일하기 위해 스캐폴드 써서 레이아웃을 통일하고 있지만 그 구현을 각 화면에서 반복하고 있다. 반복 코드로 인해 변경 사항이 생겼을 때 모든 화면을 수정해야 하는 문제가 있어 SRP(
Single Responsibility Principle)를 위반하고 있다.

Compose의 상태 호이스팅은 함수 콜스택을 활용하는 방식으로 상태를 전파하는 방식이다. 이 방식을 활용하여 `Scaffold` 레이아웃을 추상화하고 중복을 제거하고 있지만, 앱 전체적인 UI 통일감을 유지하면서
각 화면에 맞게 커스터마이징하진 못한다.

전체화면의 데이터를 담당하는 [`viewmodel`](../../app/src/main/java/com/github/hemoptysisheart/sample/viewmodel) 패키지가 상위 Compose 함수에
UI 커스터마징을 요청하는 방식으로 상태 호이스팅을 보완하는 방법으로 상태 펌프(State Pump)를 도입한다.
