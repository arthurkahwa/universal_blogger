import json

root = '04_photos'
file_fp = open('../postgres/data/' + root + '.json')
json_entries = json.load(file_fp)
sql_insert_template = 'insert into photos (id, albumid, title, url) values (_id, _fk, _title, _url)'
file_fp.close()
sql_fp = open('../postgres/' + root + '.sql', 'w')
for entry in json_entries:
    sql = sql_insert_template.replace('_id', str(entry['id']))
    sql = sql.replace('_fk', str(entry['albumId']))
    sql = sql.replace('_title', "'" + entry['title'] + "'")
    sql = sql.replace('_url', "'" + entry['url'] + "'")
    sql_fp.write(sql + ';\n')
    print(sql)
sql_fp.close()
