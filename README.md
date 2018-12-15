# Movie-info-Search
부스트캠프 사전과제

## 설명
* 네이버 검색 API를 활용하여 영화정보를 검색하는 어플리케이션 제작
* 사용자로부터 검색어를 입력받아 검색결과 목록을 표시

## 구조 및 라이브러리
* RxJava
* Retrofit
* Picasso
* Android Architecture Components (ViewModel, LiveData)
* Databinding
* MVVM 패턴

## 그 외
* API 클라이언트 객체는 Singletone 을 적용했습니다
* [SingleLiveEvent](https://github.com/googlesamples/android-architecture/blob/dev-todo-mvvm-live/todoapp/app/src/main/java/com/example/android/architecture/blueprints/todoapp/SingleLiveEvent.java)를 사용해서 click 이벤트를 observe 했습니다
