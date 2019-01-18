# Movie-info-Search
네이버 검색 API를 활용한 '영화 정보 검색 모바일 앱'

## 설명
* 사용자로부터 검색어를 입력받아 영화검색결과 목록을 RecyclerView로 보여준다

## 구조 및 라이브러리
* RxJava
* Retrofit
* Picasso
* Android Architecture Components (ViewModel, LiveData)
* Databinding
* MVVM 패턴

## 그 외
* API 클라이언트 객체는 Singleton을 적용했습니다
* [SingleLiveEvent](https://github.com/googlesamples/android-architecture/blob/dev-todo-mvvm-live/todoapp/app/src/main/java/com/example/android/architecture/blueprints/todoapp/SingleLiveEvent.java)를 사용해서 click 이벤트를 observe 했습니다
* RxJava를 사용해 백그라운드 thread-pool 에서 DiffUtil 연산을 수행했습니다
