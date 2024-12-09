# SPACE INVADERS

## 요구 사항

### 게임 진행 요구 사항
-  그래픽을 사용하여 게임을 만든다. 어떤 그림을 쓸지는 학생 자유이다.
-  별도의 시작 화면을 둔다.
-  적과 부딪히거나, 적 총알에 맞거나, 적이 바닥까지 내려올 경우 위와 같이 패배 메시지를 보여주고 게임을 다시할 것인지 물어본다.
-  적군을 모두 없애면 승리 메시지를 보여주고 게임을 다시 할 것인지 물어본다.
-  다시 한다고 할 경우 새 게임 화면을 보여주고 안 한다고 할 경우 프로그램을 종료한다.

### 플레이어 요구사항
-  방향키를 이용해서 비행기를 상, 하, 좌, 우, 대각선으로 움직일 수 있다.
-  스페이스바를 누르면 총알이 발사된다. 여러 발 발사할 수 있다.
  -  스페이스바를 계속 누르고 있어도 한발만 발사된다.
-  총알은 비행기에서 출발하여 화면 맨위까지 움직이며 그 후 사라진다.

### 적 요구사항
-  적군은 8명이며 좌우로 움직이며 점점 빠르게 내려온다.
-  적군도 총알을 여러 발 발사할 수 있다.
-  적군 총알 색깔과 내 총알 색깔은 다르다.

### 점수 요구사항
-  내가 적군을 맞히면 적군이 없어지며 점수가 10점 올라간다.
-  보너스 비행기가 화면 상단 왼쪽에서 가끔씩 나오며 맞추면 점수가 100점 올라간다.
-  최고 점수를 보여주고 파일에 저장하여 게임을 재시작해도 보여질 수 있도록 한다.


## 기능 구현 목록 정리

### 입력 기능
- [ ] 사용자의 키보드 입력을 받는 기능

### 플레이어 비행기 기능
- [ ] 플레이어를 생성하는 기능
- [ ] 상, 하, 좌, 우, 대각선으로 이동하는 기능
- [ ] 총알을 발사하는 기능

### 적 비행기 기능
- [ ] 적을 위치에 맞게 8개 생성하는 기능
- [ ] 게임 화면을 벗어나지 않도록 좌우로 움직이며 내려오는 기능
  - [ ] 점점 빨라지는 기능
- [ ] 적이 총알을 일정 확률로 발사하는 기능

### 보너스 비행기 기능
- [ ] 왼쪽 상단에서 일정 확률로 생성되는 기능
- [ ] 오른쪽으로 이동하는 기능

### 점수 기능
- [ ] 적 비행기를 맞추면 10점이 증가하는 기능
- [ ] 보너스 비행기를 맞추면 100점이 증가하는 기능
- [ ] 최고 점수를 파일에 저장하는 기능
- [ ] 최고 점수를 파일에서 불러오는 기능

### UI 기능
- [ ] 게임 시작 화면
  - [ ] Game Start 버튼 
- [ ] 플레이어 비행기를 표시하는 기능
- [ ] 플레이어 비행기의 총알을 표시하는 기능
- [ ] 적 비행기를 표시하는 기능
- [ ] 적 비행기의 총알을 표시하는 기능
- [ ] 현재 게임의 점수를 표시하는 기능
- [ ] 최고 점수를 표시하는 기능

### 게임 진행 기능
- [ ] 일정 시간마다 갱신된 게임 오브젝트 좌표를 업데이트하여 다시 그리는 기능
- [ ] 충돌을 감지하는 기능
  - [ ] 적 비행기가 플레이어 총알과 충돌
  - [ ] 플레이어 비행기가 적 총알과 충돌
  - [ ] 플레이어 비행기와 적 비행기가 충돌
- [ ] 승리 메시지를 표시하는 기능
  - [ ] 적 비행기가 모두 제거되었을 경우
- [ ] 패배 메시지를 표시하는 기능
  - [ ] 플레이어 비행기가 적 총알과 충돌할 경우
  - [ ] 적 비행기가 모두 바닥까지 내려왔을 경우
- [ ] 게임 재시작 여부를 물어보는 기능
  - [ ] 예(Y)를 누르면 게임을 재시작하는 기능
  - [ ] 아니오(N)를 누르면 게임을 종료하는 기능


