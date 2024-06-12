import json

root = '01_users'
file_fp = open('../postgres/data/' + root + '.json')
json_entries = json.load(file_fp)
sql_insert_template = 'insert into users (id, username) values (_id, _username)'
file_fp.close()
sql_fp = open('../postgres/' + root + '.sql', 'w')
for entry in json_entries:
    sql = sql_insert_template.replace('_id', str(entry['id']))
    #sql = sql.replace('_userid', str(entry['userId']))
    sql = sql.replace('_username', "'" + entry['email'] + "'")
    sql_fp.write(sql + ';\n')
    print(sql)
