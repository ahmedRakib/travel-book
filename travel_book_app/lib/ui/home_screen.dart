import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:travel_book_app/custom_widgets/campaign_list.dart';

class HomeScreen extends StatefulWidget {
  @override
  _HomeScreenState createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.cyan[700],
        title: Text("Campaign List"),
      ),
      body: SafeArea(
        child: Container(
          decoration: BoxDecoration(
              gradient: LinearGradient(
                  begin: Alignment.topRight,
                  end: Alignment.bottomLeft,
                  colors: [
                Theme.of(context).primaryColor,
                Theme.of(context).accentColor,
              ])),
          child: Column(
            //padding: EdgeInsets.symmetric(vertical: 25.0),
            children: <Widget>[
              SizedBox(height: 20.0),
              _searchBar(),
              SizedBox(height: 20.0),
              CampaignList(),
            ],
          ),
        ),
      ),
    );
  }

  _searchBar() {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Container(
        decoration: BoxDecoration(
            borderRadius: BorderRadius.all(Radius.circular(20)),
            color: Colors.white),
        child: TextField(
          keyboardType: TextInputType.text,
          style: TextStyle(
            fontSize: 20.0,
          ),
          textAlign: TextAlign.left,
          decoration: InputDecoration(
            hintText: 'Where Do You Wanna Go?',
            suffixIcon: Icon(Icons.search),
            contentPadding: EdgeInsets.fromLTRB(30.0, 10.0, 20.0, 10.0),
            border: OutlineInputBorder(borderRadius: BorderRadius.circular(20.0)),
          ),
          onChanged: (text) {
            text = text.toLowerCase();
            debugPrint(text.toString());
            setState(() {
              // _notesForDisplay = _notes.where((note) {
              //   var noteTitle = note.title.toLowerCase();
              //   return noteTitle.contains(text);
              // }).toList();
            });
          },
        ),
      ),
    );
  }
}
