# -*- coding: utf-8 -*-
from plistlib import Data

import pymysql
import socket
import time


# 스크립트 시작
total_start = time.time()
total_start_time = time.strftime("[%y-%m-%d] %X", time.localtime())





# Local DB
connect = pymysql.connect(host='localhost',
                          user='root', password='han1280', db='takealook', charset='utf8', local_infile=1)
# RDS SERVER DB
# connect = pymysql.connect(host='takealook.cjdwnzzk2agh.ap-northeast-2.rds.amazonaws.com',
#                           user='tal_admin', password='take1234', db='takealook', charset='utf8', local_infile=1)

cursor = connect.cursor(pymysql.cursors.DictCursor)



# 오늘 날짜 함수
def get_today():
    now = time.localtime()
    today = "%04d-%02d-%02d" % (now.tm_year, now.tm_mon, now.tm_mday)
    today_ = today + "/"
    return today_

# 날짜 함수 -> 변수 대입
today_dir = get_today()

user_name = socket.gethostname() # 컴퓨터 user 이름
dataset_path = "C:/Users/" + user_name + "/Desktop/Dataset/"

# datasetTSV = ["basic_titles.tsv", "crew.tsv", "ratings.tsv",
#                "principals.tsv", "title_akas.tsv", "basic_names.tsv"]

datasetTSV = ["basic_titles.tsv", "crew.tsv", "ratings.tsv",
               "basic_names.tsv", "principals.tsv"]

# 리스트 초기화
DataSet = [0]*5
datasetName = [0]*5
datasetLoad = [0]*5
DropResult = [0]*6


# Block 1 start
print("\nBlock 1 start")
start_time = time.time()
print(time.strftime("[%y-%m-%d] %X", time.localtime())) # 현재시간 출력

for i in range(0, 5):
    input_time_start = time.time()
    ds = datasetTSV[i]
    print("\n# [" + str(i+1), ds + "] Load")
    datasetName[i] = ds[:-4]
    # sql
    datasetLoad = """
            LOAD DATA LOCAL INFILE     '""" + dataset_path + today_dir + datasetTSV[i] + """'
            INTO TABLE                 """ + datasetName[i] + """
            CHARACTER SET 			   utf8
            COLUMNS TERMINATED BY      '\t'
            LINES TERMINATED BY        '\n'
            IGNORE                     1 LINES;
        """
    DataSet[i] = str(datasetLoad)


    # Dataset Upload
    print("\n# [" + datasetName[i] + "] input start")
    sql = DataSet[i]
    cursor.execute(sql)
    connect.commit()
    print(sql)
    print("ㅡㅡㅡ #Commit ㅡㅡㅡ\n")
    # input time
    input_time = time.time()
    print(time.strftime("[%y-%m-%d] %X", time.localtime()))  # 현재시간 출력
    print("[" + datasetName[i] + "] 소요시간 : %s초" \
          %(round(input_time - input_time_start, 1)))

# Block 1 end
print("\nBlock 1 end")
end_time = time.time()
print(time.strftime("[%y-%m-%d] %X", time.localtime())) # 현재시간 출력

# 소요시간
print("소요시간 : %s초" %round(end_time - start_time, 1))
print("\n")


Dtable = ["basic_titles", "basic_titles", "basic_titles", "basic_names", "basic_names"]
Dcolumn = ["isAdult", "endYear", "runtimeMinutes", "primaryProfession", "knownForTitles"]

# Dtable = ["title_akas", "title_akas", "basic_titles", "basic_titles", "basic_titles", "basic_names", "basic_names"]
# Dcolumn = ["types", "attributes", "isAdult", "endYear", "runtimeMinutes", "primaryProfession", "knownForTitles"]

# Table drop Column (7개)
print("\nㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ")
for i in range(0, 5):
    drop_time_start = time.time()
    # sql
    DropColumn = """
            ALTER TABLE """ + Dtable[i] + """
            DROP COLUMN """ + Dcolumn[i] + """
        """
    DropResult[i] = str(DropColumn)

    # Drop the table row
    print("# " + str(i+1) + " [" + Dtable[i] + "] ALTER table, [" + Dcolumn[i] + "] DROP column start")
    sql = DropResult[i]
    cursor.execute(sql)
    connect.commit()
    print("ㅡㅡㅡ #Commit ㅡㅡㅡ\n")
    # drop time
    drop_time = time.time()
    print(time.strftime("[%y-%m-%d] %X", time.localtime()))  # 현재시간 출력
    print("[" + Dtable[i] + " Table " + Dcolumn[i] + " column " + "] 소요시간 : %s초" \
          %(round(drop_time - drop_time_start, 1)))


# processing end
total_end = time.time()
total_end_time = time.strftime("[%y-%m-%d] %X", time.localtime())

# print processing time
print("%s ~ %s, 총 소요시간 : %s초" \
    %(total_start_time, total_end_time, round(total_end - total_start, 1)))

# SQL SERVER disconnect
connect.close()