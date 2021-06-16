import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:travel_book_app/model/campaign.dart';

class CampaignList extends StatefulWidget {
  @override
  _CampaignListState createState() => _CampaignListState();
}

class _CampaignListState extends State<CampaignList> {
  @override
  Widget build(BuildContext context) {
    return Expanded(
      child: ListView.builder(
        padding: EdgeInsets.only(top: 0.0, bottom: 15.0),
       // scrollDirection: Axis.vertical,
        //shrinkWrap: true,
        itemCount: 15,// it will be length of the list
        itemBuilder: (BuildContext context, int index){
          Campaign  campaign = new Campaign();
          campaign.id = 1;
          campaign.name = "Fulda Tour";
          campaign.details = "A day long tour in fulda";
          campaign.budget = 100;
          return Stack(
            children: <Widget> [
              Container(
                margin: EdgeInsets.fromLTRB(10.0, 5.0, 20.0, 5.0),
                height: 140.0,
                width: double.infinity,
                decoration: BoxDecoration(
                  color: Colors.white54,
                  borderRadius: BorderRadius.circular(20.0),
                ),
                child: Padding(
                  padding:  EdgeInsets.fromLTRB(128, 3.0, 20.0, 5.0),
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: <Widget> [
                      Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: <Widget> [
                          Container(
                              width:120.0,
                              child: Text(
                                campaign.name,
                                maxLines: 2,
                                overflow: TextOverflow.ellipsis,
                                style: TextStyle(fontSize: 18, fontWeight: FontWeight.w600),
                              )
                          ),
                          Text(
                            '\$${campaign.budget}',
                            style: TextStyle(fontSize: 22.0, fontWeight: FontWeight.w700),
                          ),
                        ],
                      ),
                      Text(campaign.details, style: TextStyle(color: Colors.grey[800]),),
                      SizedBox(height: 10.0),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.end,
                        children: [
                          FlatButton(
                            onPressed: () {
                              // Navigator.push.to details page
                               },
                            child: Text("Details", style: TextStyle(fontSize:17.0,fontWeight: FontWeight.bold, color: Colors.blue[800])),
                          ),
                        ],
                      ),
                    ],
                  ),
                )
              ),
              Positioned(
                left: 10.0,
                top: 5.0,
                bottom: 5.0,
                child: Container(
                  //margin: EdgeInsets.fromLTRB(10.0,0, 0, 5.0),
                  child: ClipRRect(
                    borderRadius: BorderRadius.circular(20.0),
                    child: Image(
                      width: 110.0,
                      image: AssetImage('assets/images/Fulda2.jpg'),
                      fit: BoxFit.cover,
                    ),

                  ),
                ),
              )
            ],
          );
      },
      ),
    );
  }
}
