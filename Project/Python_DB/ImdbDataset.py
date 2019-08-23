import zipfile
import requests

# code.py
# Link:https://datasets.imdbws.com/

from requests import get  # to make GET request

def download(url, file_name):
    with open(file_name, "wb") as file:   # open in binary mode
        response = get(url)               # get request
        file.write(response.content)      # write to file

# DataSet 파일을 받을 url 경로
download_url = "https://datasets.imdbws.com/"

# DB에 테이블을 만들 dataset
dataset = ["title.basics.tsv.gz", "name.basics.tsv.gz", "title.crew.tsv.gz",
           "title.principals.tsv.gz", "title.ratings.tsv.gz", "title.akas.tsv.gz"]

datasetname = ["basic_titles.zip", "basic_names.zip", "crew.zip",
               "principals.zip", "ratings.zip", "title_akas.zip"]

# https://cinema4dr12.tistory.com/1296 폴더생성
try:
    if not(os.path.isdir({YOUR_DIRECTORY_NAME})):
        os.makedirs(os.path.join({YOUR_DIRECTORY_NAME}))
except OSError as e:
    if e.errno != errno.EEXIST:
        print("Failed to create directory!!!!!")
        raise


# Dataset DownLoad
if __name__ == '__main__':
    for i in range(6):
        url = download_url + dataset[i] # 다운받기
        download(url,datasetname[i])
        print(str(i+1) + " " + datasetname[i] + "을 생성했습니다.")
    print("모든 Dataset zip 파일을 다운받았습니다.")

# 알집해제할 기본경로
default_path = "C:\\Users\\JJunJang\\Desktop\\git\\take_a_look_Dev\\Project\\"

# Dataset Unzip
if __name__ == '__main__':
    for j in range(6):
        dataset_zip = zipfile.ZipFile(default_path + datasetname[j]) # 알집풀기
        dataset_zip.extract(datasetname[j], default_path)
        print(str(j+1) + " " + datasetname[j] + "알집을 풀었습니다.")

    dataset_zip.close()
    print("모든 알집을 압축해제 했습니다..")


