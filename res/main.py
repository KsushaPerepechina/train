from lxml import etree
import random

arrival_station = [
    "Brest",
    "Minsk",
    "Grodno",
    "Homel",
    "Mogilev",
    "Vitebsk",
    "St Petersburg",
    "Orsha",
    "Kaliningrad"
]
departure_station = [
    "Minsk",
    "Vilnius",
    "Moscow",
    "Baranovichi",
    "Molodechno",
    "Borisov",
    "Osipovichi",
    "Zhodino",
    "Smolensk"
]

departure_dateTime = [
    "01.06.2018 19:08"
    "03.06.2018 18:54"
    "02.06.2018 0:11"
    "04.06.2018 22:12"
    "11.06.2018 0:25"
    "05.06.2018 20:18"
    "07.06.2018 0:30"
    "10.06.2018 13:08"
    "09.06.2018 21:17"
    "15.06.2018 14:25"
    "31.05.2018 14:25"
    "14.06.2018 5:11"
    "22.06.2018 14:03"
    "24.05.2018 0:06"
    "15.06.2018 5:25"
    "18.06.2018 7:16"
    "27.06.2018 7:23"
]

arrival_dateTime = [
    "02.06.2018 6:01"
    "04.06.2018 6:01"
    "02.06.2018 1:49"
    "05.06.2018 0:22"
    "11.06.2018 5:51"
    "06.06.2018 0:27"
    "07.06.2018 2:10"
    "11.06.2018 9:16"
    "10.06.2018 1:05"
    "16.06.2018 5:40"
    "01.06.2018 8:03"
    "14.06.2018 7:12"
    "23.06.2018 5:22"
    "24.05.2018 5:13"
    "15.06.2018 6:22"
    "18.06.2018 8:06"
    "27.06.2018 9:24"
]

travel_time = [
    "10:53"
    "11:07"
    "1:38"
    "2:10"
    "5:26"
    "4:09"
    "1:40"
    "20:08"
    "03:48"
    "15:15"
    "17:38"
    "2:01"
    "15:19"
    "5:07"
    "0:57"
    "0:50"
    "2:01"
]

root = etree.Element('list')
for i in range(10):
    train = etree.Element('train', id=str(i))
    number = etree.Element('number')
    departureStation = etree.Element('departureStation')
    departureStation.text = departure_station[random.randint(0, len(departure_station) - 1)]
    arrivalStation = etree.Element('arrivalStation')
    arrivalStation.text = arrival_station[random.randint(0, len(arrival_station) - 1)]
    departureDateTime = etree.Element('departureDateTime')
    departureDateTime.text = departure_dateTime[random.randint(0, len(departure_dateTime) - 1)]
    arrivalDateTime = etree.Element('arrivalDateTime')
    arrivalDateTime.text = arrival_dateTime[random.randint(0, len(arrival_dateTime) - 1)]
    travelTime = etree.Element('travelTime')
    travelTime.text = travel_time[random.randint(0, len(travel_time) - 1)]
    train.append(number)
    train.append(departureStation)
    train.append(arrivalStation)
    train.append(departureDateTime)
    train.append(arrivalDateTime)
    train.append(travelTime)
    root.append(train)

s = etree.tostring(root, pretty_print=False)
print(s)
with open("pro.xml", "wb") as xml:
    xml.write(s)
