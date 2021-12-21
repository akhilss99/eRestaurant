from pyrebase import pyrebase
from onesignal import OneSignal, SegmentNotification
import datetime

firebaseConfig = {} #config file that can be fetched from firebase user dashboard

#One Signal Credentials
APP_ID = "appid"
REST_API_KEY = "restapikey"
PLAYER_ID = "playerid"
firebase = pyrebase.initialize_app(firebaseConfig)
db = firebase.database()
 


def addSecs(tm, secs):
    fulldate = datetime.datetime(100, 1, 1, tm.hour, tm.minute, tm.second)
    fulldate = fulldate + datetime.timedelta(seconds=secs)
    return fulldate.time()



def send_notification(heading, message):
    a = datetime.datetime.now().time()
    b = str(addSecs(a, 10))
    print(b)

    client = OneSignal(APP_ID, REST_API_KEY)
    notification_to_all_users = SegmentNotification(
        headings={
            "en": heading
        },
        contents={
            "en": message
        },
        included_segments=["PushNotification"],
        priority = 10,
        url="https://github.com",
        delayed_option='timezone',
        delivery_time_of_day=b
    )
    print(client.send(notification_to_all_users))

def write_milestones(milestone):
    db.child('milestones').update({milestone : True})


def milestone_handler():
    milestone_status = db.child('milestones').get().val()
    return milestone_status

def stream_handler(message):
    if(message['event'] =='put'):
        try:
            updatedprice = message['data']
            status = milestone_handler()
            m1 = status['milestone1']
            m2 = status['milestone2']
            m3 = status['milestone3']
            m4 = status['milestone4']
            print(m1, m2, m3, m4)
            print(updatedprice)
            if(m1==False and int(updatedprice)>=2000):
                write_milestones('milestone1')
                print('Notification Sent for Milestone 1!')
                send_notification('Your First Milestone!', 'Congratulations, you\'ve completed your First Milestone. Contact us through the app dashboard to redeem your voucher id: xxxxx')
            elif((m2==False and m1==True) and int(updatedprice)>=5000):
                write_milestones('milestone2')
                print('Notification Sent for Milestone 2!')
                send_notification('Your Second Milestone!', 'Congratulations, you\'ve completed your Second Milestone. Contact us through the app dashboard to redeem your voucher id: xxxxx')
            elif((m3==False and m2==True) and int(updatedprice)>=8000):
                write_milestones('milestone3')
                print('Notification Sent for Milestone 3!')
                send_notification('Your Third Milestone!', 'Congratulations, you\'ve completed your Third Milestone. Contact us through the app dashboard to redeem your voucher id: xxxxx')
            elif((m4==False and m3==True) and int(updatedprice)>=10000):
                write_milestones('milestone4')
                print('Notification Sent for Milestone 4!')
                send_notification('Your Fourth Milestone!', 'Congratulations, you\'ve completed your Fourth Milestone. Contact us through the app dashboard to redeem your voucher id: xxxxx')
        except KeyError:
            pass

firebase_stream = db.child('Bill').stream(stream_handler)

"""
milestone1 --> >2000
milestone2 --> >5000
milestone3 --> >8000
milestone4 --> >10000

"""