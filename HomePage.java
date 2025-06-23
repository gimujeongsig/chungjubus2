import 'package:flutter/material.dart';
import 'package:flutter_map/flutter_map.dart'; // FlutterMap 사용을 위해 필요
import 'package:latlong2/latlong.dart';     // 위경도 값 사용을 위해 필요
// Supabase 관련 임포트(supabase_flutter)는 제거했습니다.

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  // Supabase 클라이언트 인스턴스 제거
  // final supabase = Supabase.instance.client;

  // 지도에 표시될 마커 리스트는 현재 비어 있습니다 (Supabase 사용 안 함)
  List<Marker> _markers = [];

  // 충주 시내 중심 좌표를 설정합니다.
  final LatLng initialChungjuPosition = LatLng(36.9910, 127.9250);

  @override
  void initState() {
    super.initState();
    // 데이터 로딩 함수 호출 제거 (_fetchBusStations)
    // _fetchBusStations();
  }

  // Supabase에서 데이터를 가져오는 함수는 제거하거나 주석 처리합니다.
  // Future<void> _fetchBusStations() async {
  //   // 이 함수는 Supabase를 사용하지 않으므로 비활성화됩니다.
  // }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('충주 통학버스 지도 (Supabase 제외)'), // 앱바 제목 변경
        backgroundColor: Colors.blueAccent, // 앱바 배경색
      ),
      body: Stack(
        children: [
          // FlutterMap 위젯은 그대로 유지합니다.
          FlutterMap(
            options: MapOptions(
              center: initialChungjuPosition, // 지도의 초기 중심 좌표 (충주)
              zoom: 13, // 초기 줌 레벨
            ),
            children: [
              // 지도 타일 레이어 (OpenStreetMap)
              TileLayer(
                urlTemplate: 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
                subdomains: const ['a', 'b', 'c'],
              ),
              // MarkerLayer는 존재하지만, _markers 리스트가 비어 있으므로 마커는 표시되지 않습니다.
              // 만약 직접 마커를 추가하고 싶다면 여기에 Marker 객체를 수동으로 추가할 수 있습니다.
              MarkerLayer(markers: _markers),
            ],
          ),
          // 상단의 검색 바 UI는 그대로 유지됩니다.
          Positioned(
            top: 40,
            left: 16,
            right: 16,
            child: Container(
              padding: const EdgeInsets.symmetric(horizontal: 12),
              height: 50,
              decoration: BoxDecoration(
                color: Colors.white,
                borderRadius: BorderRadius.circular(25),
                boxShadow: [
                  BoxShadow(
                    color: Colors.black.withOpacity(0.1),
                    blurRadius: 6,
                    offset: const Offset(0, 3),
                  )
                ],
              ),
              child: Row(
                children: [
                  const Icon(Icons.school, color: Colors.orange), // 학교 아이콘
                  const SizedBox(width: 8),
                  const Expanded(
                    child: Text(
                      "여기서 검색", // 검색 텍스트
                      style: TextStyle(fontSize: 16, color: Colors.grey),
                    ),
                  ),
                  IconButton(
                    icon: const Icon(Icons.mic, color: Colors.black54), // 마이크 아이콘
                    onPressed: () {
                      // 마이크 버튼 클릭 시 동작
                    },
                  ),
                  IconButton(
                    icon: const Icon(Icons.face, color: Colors.black54), // 얼굴 아이콘
                    onPressed: () {
                      // 얼굴 아이콘 클릭 시 동작
                    },
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}