import 'package:flutter/material.dart';
import 'package:flutter_dotenv/flutter_dotenv.dart';
import 'package:supabase_flutter/supabase_flutter.dart';
import 'home_page.dart'; // HomePage 위젯 임포트
import 'package:chungjubus/supabase_flutter.dart';

Future<void> main() async {
  // Flutter 앱 초기화를 위한 위젯 바인딩 보장
  WidgetsFlutterBinding.ensureInitialized();

  // .env 파일 로드 (환경 변수 사용을 위해 필요)
  await dotenv.load(fileName: ".env");

  // Supabase 초기화: .env 파일에서 URL과 Anon Key를 가져와 사용
  await Supabase.initialize(
    url: dotenv.env['SUPABASE_URL']!,
    anonKey: dotenv.env['SUPABASE_ANON_KEY']!,
  );

  // 앱 실행
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false, // 디버그 배너 숨기기
      title: '충주 버스 안내 앱', // 앱 제목
      theme: ThemeData(
        useMaterial3: true, // Material 3 디자인 사용
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.teal), // 테마 색상 설정
      ),
      // 초기 라우트를 '/map'으로 설정합니다. 앱 시작 시 이 라우트로 이동합니다.
      initialRoute: '/map',
      // 앱의 라우트들을 정의합니다.
      routes: {
        // '/map' 라우트가 호출될 때 HomePage 위젯을 빌드하도록 설정합니다.
        '/map': (context) => const HomePage(),
        // 여기에 다른 라우트들을 추가할 수 있습니다. 예:
        // '/settings': (context) => const SettingsScreen(),
        // '/detail': (context) => const DetailScreen(),
      },
      // `home` 속성은 `initialRoute`를 사용할 경우 제거하거나 `routes`와 함께 사용하지 않는 것이 좋습니다.
      // home: const HomePage(), // initialRoute를 사용하므로 이 줄은 제거하거나 주석 처리합니다.
    );
  }
}