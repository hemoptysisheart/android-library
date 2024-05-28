# Android 라이브러리

간단한 Android용 라이브러리를 개발하는 레포지토리.

## 문서

- [개발 문서](./doc/README.md)

## 개발환경

1. `git clone git@github.com:hemoptysisheart/android-library.git`
2. [`sample-local.properties`](./sample-local.properties) 파일를 참고해서 `local.properties`를 작성 혹은 수정.
    - `publish.enable=true` : 리모트 Maven 저장소에 라이브러리를 다운로드해서 빌드. 의존성이 있는 라이브러리가 배포된 상태임을 보장한다.
    - `publish.enable=false` : 개발모드. 서브프로젝트에서 직접 의존성을 지정해서 배포없이 빌드.

## 샘플 앱

다음 조건을 충족하는 로컬 메모리만으로 가능한 미로찾기 앱.

1. 라이브러리 모듈 사용.
2. 공개한 라이브러리를 다운로드해서 사용.
