import json

root = '05_posts'
file_fp = open('../postgres/data/' + root + '.json')
json_entries = json.load(file_fp)
sql_insert_template = 'insert into posts (id, userid, title, body) values (_id, _userid, _title, _body)'
file_fp.close()
sql_fp = open('../postgres/' + root + '.sql', 'w')
sql_fp.write('BEGIN;\n')
count = 0
for entry in json_entries:
    sql = sql_insert_template.replace('_id', str(entry['id']))
    sql = sql.replace('_userid', str(entry['userId']))
    sql = sql.replace('_title', "'" + entry['title'] + "'")
    sql = sql.replace('_body', "'" + entry['body'] + "'")
    # sql = sql.replace('_thumbnailUrl', "'" + entry['thumbnailUrl'] + "'")
    sql_fp.write(sql + ';\n')
    print(sql)
    count += 1
    if count % 100 == 0:
        sql_fp.write('COMMIT;\nBEGIN;\n')
sql_fp.write('COMMIT;\n')
sql_fp.close()
