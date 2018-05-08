/**
 * OpenAPI Petstore
 * This is a sample server Petstore server. For this sample, you can use the api key `special-key` to test the authorization filters.
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


#include "SWGTag.h"

#include "SWGHelpers.h"

#include <QJsonDocument>
#include <QJsonArray>
#include <QObject>
#include <QDebug>

namespace Swagger {

SWGTag::SWGTag(QString json) {
    init();
    this->fromJson(json);
}

SWGTag::SWGTag() {
    init();
}

SWGTag::~SWGTag() {
    this->cleanup();
}

void
SWGTag::init() {
    id = 0L;
    m_id_isSet = false;
    name = new QString("");
    m_name_isSet = false;
}

void
SWGTag::cleanup() {

    if(name != nullptr) { 
        delete name;
    }
}

SWGTag*
SWGTag::fromJson(QString json) {
    QByteArray array (json.toStdString().c_str());
    QJsonDocument doc = QJsonDocument::fromJson(array);
    QJsonObject jsonObject = doc.object();
    this->fromJsonObject(jsonObject);
    return this;
}

void
SWGTag::fromJsonObject(QJsonObject pJson) {
    ::Swagger::setValue(&id, pJson["id"], "qint64", "");
    
    ::Swagger::setValue(&name, pJson["name"], "QString", "QString");
    
}

QString
SWGTag::asJson ()
{
    QJsonObject obj = this->asJsonObject();
    QJsonDocument doc(obj);
    QByteArray bytes = doc.toJson();
    return QString(bytes);
}

QJsonObject
SWGTag::asJsonObject() {
    QJsonObject obj;
    if(m_id_isSet){
        obj.insert("id", QJsonValue(id));
    }
    if(name != nullptr && *name != QString("")){
        toJsonValue(QString("name"), name, obj, QString("QString"));
    }

    return obj;
}

qint64
SWGTag::getId() {
    return id;
}
void
SWGTag::setId(qint64 id) {
    this->id = id;
    this->m_id_isSet = true;
}

QString*
SWGTag::getName() {
    return name;
}
void
SWGTag::setName(QString* name) {
    this->name = name;
    this->m_name_isSet = true;
}


bool
SWGTag::isSet(){
    bool isObjectUpdated = false;
    do{
        if(m_id_isSet){ isObjectUpdated = true; break;}
        if(name != nullptr && *name != QString("")){ isObjectUpdated = true; break;}
    }while(false);
    return isObjectUpdated;
}
}

