import 'package:supabase_flutter/supabase_flutter.dart';
import 'bus_station.dart';

class SupabaseService {
  final _client = Supabase.instance.client;

  Future<List<BusStation>> fetchBusStations() async {
    final response = await _client.from('stations').select();

    return (response as List)
        .map((data) => BusStation.fromMap(data))
        .toList();
  }
}
