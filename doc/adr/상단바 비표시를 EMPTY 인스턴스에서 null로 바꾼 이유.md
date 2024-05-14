# 상단바 비표시를 `EMPTY` 인스턴스에서 `null`로 바꾼 이유

화면마다 상단바의 종류나 표시 여부가 고정되어 있다는 가정으로 `TopBarState.EMPTY`인스턴스와 `ViewModel.topBar`를 만들었지만,
다음의 이유로 `EMPTY` 인스턴스를 없애고 `ViewModel`인스턴스가 상단바 종류를 지정하도록 변경.

1. nullable인 상태를 `StateFlow`를 `@Composable`에서 not-null로 다운캐스팅을 어떻게 해야 하지?
2. 상단 바를 일시적으로 표시하지 않고 화면을 확장하는 방식을 추가하려면?

## 참고

### 관련 소스

- [`TopBarState`](../../ui-state/src/main/java/com/github/hemoptysisheart/ui/state/scaffold/TopBarState.kt)
- [`ViewModel`](../../viewmodel/src/main/java/com/github/hemoptysisheart/viewmodel/ViewModel.kt)
