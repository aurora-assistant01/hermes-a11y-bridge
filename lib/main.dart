import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() => runApp(const _App());

class _App extends StatelessWidget {
  const _App();
  @override
  Widget build(BuildContext context) => const MaterialApp(home: _Home());
}

class _Home extends StatefulWidget {
  const _Home();
  @override
  State<_Home> createState() => _HomeState();
}

class _HomeState extends State<_Home> {
  static const _channel = MethodChannel('com.hermes.a11y/bridge');
  String _last = 'idle';

  Future<void> _tap(int cx, int cy) async {
    setState(() => _last = 'tap $cx,$cy');
    try {
      final r = await _channel.invokeMethod('tap', {'x': cx, 'y': cy});
      setState(() => _last = 'tap ok: $r');
    } on PlatformException catch (e) {
      setState(() => _last = 'tap err: ${e.message}');
    }
  }

  Future<void> _back() async {
    setState(() => _last = 'back');
    try {
      final r = await _channel.invokeMethod('back');
      setState(() => _last = 'back ok: $r');
    } on PlatformException catch (e) {
      setState(() => _last = 'back err: ${e.message}');
    }
  }

  @override
  Widget build(BuildContext context) => Scaffold(
        appBar: AppBar(title: const Text('Hermes A11y Bridge')),
        body: Padding(
          padding: const EdgeInsets.all(16),
          child: Column(
            children: [
              Text('Last: $_last'),
              const SizedBox(height: 12),
              Row(
                children: [
                  ElevatedButton(onPressed: () => _tap(540, 900), child: const Text('Tap center')),
                  const SizedBox(width: 8),
                  ElevatedButton(onPressed: _back, child: const Text('Back')),
                ],
              ),
            ],
          ),
        ),
      );
}
