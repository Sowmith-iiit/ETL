# -*- coding: utf-8 -*-
# this file is released under public domain and you can use without limitations

#########################################################################
## This is a sample controller
## - index is the default action of any application
## - user is required for authentication and authorization
## - download is for downloading files uploaded in the db (does streaming)
## - call exposes all registered services (none by default)
#########################################################################


def index():
    """
    example action using the internationalization operator T and flash
    rendered by views/default/index.html or views/generic.html

    if you need a simple wiki simply replace the two lines below with:
    return auth.wiki()
    """
    response.flash = T("Welcome to NoteKeeper!")
    return dict(message=T(''))


def user():
    """
    exposes:
    http://..../[app]/default/user/login
    http://..../[app]/default/user/logout
    http://..../[app]/default/user/register
    http://..../[app]/default/user/profile
    http://..../[app]/default/user/retrieve_password
    http://..../[app]/default/user/change_password
    http://..../[app]/default/user/manage_users (requires membership in
    use @auth.requires_login()
        @auth.requires_membership('group name')
        @auth.requires_permission('read','table name',record_id)
    to decorate functions that need access control
    """
    return dict(form=auth())

@cache.action()
def download():
    """
    allows downloading of uploaded files
    http://..../[app]/default/download/[filename]
    """
    return response.download(request, db)


def call():
    """
    exposes services. for example:
    http://..../[app]/default/call/jsonrpc
    decorate with @services.jsonrpc the functions to expose
    supports xml, json, xmlrpc, jsonrpc, amfrpc, rss, csv
    """
    return service()


@auth.requires_signature()
def data():
    """
    http://..../[app]/default/data/tables
    http://..../[app]/default/data/create/[table]
    http://..../[app]/default/data/read/[table]/[id]
    http://..../[app]/default/data/update/[table]/[id]
    http://..../[app]/default/data/delete/[table]/[id]
    http://..../[app]/default/data/select/[table]
    http://..../[app]/default/data/search/[table]
    but URLs must be signed, i.e. linked with
      A('table',_href=URL('data/tables',user_signature=True))
    or with the signed load operator
      LOAD('default','data.load',args='tables',ajax=True,user_signature=True)
    """
    return dict(form=crud())

def dispnotes():
	return dict()

def makenotes():
    return dict()

def notes():
    return dict()

def todolist():
    if request.vars.sub=="Add to List":
        val=False
        db.ToDoList.insert(checkbox=val,task=request.vars.content,priority=request.now)
    return dict()

def todolistprint():
    s=""
    new=db().select(db.ToDoList.ALL,orderby=~db.ToDoList.priority)
    for i in new:
        if i.checkbox==True:
            s+="<p id=p" + str(i.id)  + " style='text-decoration:line-through' ><input type='checkbox' id=" + str(i.id) + " onchange='save(" + str(i.id) + ");' checked=true /> " + i.task + " <input type='button' onclick='delitem(" + str(i.id) + ")' value=' X ' /></p><br>"
        else:
            s+="<p id=p" + str(i.id)  + " style='text-decoration:none' ><input type='checkbox' id=" + str(i.id) + " onchange='save(" + str(i.id) + ");' /> " + i.task + " <input type='button' onclick='delitem(" + str(i.id) + ")' value=' X ' /></p><br>"
    return s

def updatelist():
    n=request.vars.n
    db(db.ToDoList.id==int(n)).update(checkbox=request.vars.checked,priority=request.now)

def removelist():
    n=request.vars.n
    db(db.ToDoList.id==int(n)).delete()

def drawing():
    if request.vars.canvas_image!=None:
        session.Text=request.vars.Text
        session.canvas_image=request.vars.canvas_image
        session.canvas_height=request.vars.canvas_height
    if request.vars.sub=="SAVE":
        db.Canvas.insert(textfield=request.vars.Text,canvas_image=request.vars.canvas_image,canvas_height=request.vars.canvas_height)
    return dict()

def canvas():
    new=db().select(db.Canvas.ALL)
    return dict(new=new)
