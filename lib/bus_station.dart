class BusStation {
  final String name;
  final double latitude;
  final double longitude;

  BusStation({
    required this.name,
    required this.latitude,
    required this.longitude,
  });

  factory BusStation.fromMap(Map<String, dynamic> map) {
    return BusStation(
      name: map['name'] ?? '이름 없음',
      latitude: map['latitude'],
      longitude: map['longitude'],
    );
  }
}
