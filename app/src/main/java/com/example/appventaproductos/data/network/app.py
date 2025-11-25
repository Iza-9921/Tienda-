from flask import Flask, jsonify, request, send_from_directory, abort
from flask_sqlalchemy import SQLAlchemy
from flask_cors import CORS
from werkzeug.utils import secure_filename
from werkzeug.exceptions import RequestEntityTooLarge
import os
from typing import Optional

# ---------- Config ----------
BASE_DIR = os.path.abspath(os.path.dirname(__file__))
UPLOAD_FOLDER = os.path.join(BASE_DIR, "uploads")
DB_PATH = os.path.join(BASE_DIR, "tienda_bebes.db")
ALLOWED_EXTENSIONS = {"png", "jpg", "jpeg", "gif"}
MAX_CONTENT_LENGTH = 16 * 1024 * 1024  # 16 MB

app = Flask(__name__)
CORS(app)  # Ajusta opciones si necesitas restringir orígenes

app.config["SQLALCHEMY_DATABASE_URI"] = f"sqlite:///{DB_PATH}"
app.config["SQLALCHEMY_TRACK_MODIFICATIONS"] = False
app.config["UPLOAD_FOLDER"] = UPLOAD_FOLDER
app.config["MAX_CONTENT_LENGTH"] = MAX_CONTENT_LENGTH

db = SQLAlchemy(app)
os.makedirs(app.config["UPLOAD_FOLDER"], exist_ok=True)

# ---------- Models ----------
class Ropa(db.Model):
    __tablename__ = "ropa"
    id = db.Column(db.Integer, primary_key=True)
    nombre = db.Column(db.String(100))
    talla = db.Column(db.String(20))
    precio = db.Column(db.Float, default=0.0)
    imagen_url = db.Column(db.String(300), nullable=True)

class Carriola(db.Model):
    __tablename__ = "carriola"
    id = db.Column(db.Integer, primary_key=True)
    marca = db.Column(db.String(100))
    modelo = db.Column(db.String(100))
    precio = db.Column(db.Float, default=0.0)
    imagen_url = db.Column(db.String(300), nullable=True)

class Accesorio(db.Model):
    __tablename__ = "accesorio"
    id = db.Column(db.Integer, primary_key=True)
    nombre = db.Column(db.String(100))
    tipo = db.Column(db.String(50))
    precio = db.Column(db.Float, default=0.0)
    imagen_url = db.Column(db.String(300), nullable=True)

with app.app_context():
    db.create_all()

# ---------- Helpers ----------
def allowed_file(filename: str) -> bool:
    return "." in filename and filename.rsplit(".", 1)[1].lower() in ALLOWED_EXTENSIONS

def save_uploaded_file(file_storage) -> Optional[str]:
    """
    Guarda el archivo subido de forma segura y devuelve la ruta pública relativa (/uploads/archivo).
    Devuelve None si el archivo no es válido.
    """
    if not file_storage:
        return None
    if not getattr(file_storage, "filename", None):
        return None
    if not allowed_file(file_storage.filename):
        return None

    filename = secure_filename(file_storage.filename)
    base, ext = os.path.splitext(filename)
    save_path = os.path.join(app.config["UPLOAD_FOLDER"], filename)

    counter = 1
    # Evitar sobreescritura
    while os.path.exists(save_path):
        filename = f"{base}_{counter}{ext}"
        save_path = os.path.join(app.config["UPLOAD_FOLDER"], filename)
        counter += 1

    try:
        file_storage.save(save_path)
    except Exception:
        return None

    # Devuelve la ruta que el cliente puede pedir (por ejemplo: /uploads/nombre.jpg)
    return f"/uploads/{filename}"

def parse_price(value) -> float:
    try:
        if value is None:
            return 0.0
        return float(value)
    except (ValueError, TypeError):
        return 0.0

# ---------- Static file serving ----------
@app.route("/uploads/<path:filename>", methods=["GET"])
def serve_uploads(filename):
    # Protección básica contra traversal
    if ".." in filename or filename.startswith("/"):
        abort(400)
    return send_from_directory(app.config["UPLOAD_FOLDER"], filename)

# ---------- GET endpoints ----------
@app.route("/ropa", methods=["GET"])
def get_ropa():
    lista = Ropa.query.all()
    return jsonify([
        {"id": r.id, "nombre": r.nombre, "talla": r.talla, "precio": r.precio, "imagen_url": r.imagen_url}
        for r in lista
    ]), 200

@app.route("/carriolas", methods=["GET"])
def get_carriolas():
    lista = Carriola.query.all()
    return jsonify([
        {"id": c.id, "marca": c.marca, "modelo": c.modelo, "precio": c.precio, "imagen_url": c.imagen_url}
        for c in lista
    ]), 200

@app.route("/accesorios", methods=["GET"])
def get_accesorios():
    lista = Accesorio.query.all()
    return jsonify([
        {"id": a.id, "nombre": a.nombre, "tipo": a.tipo, "precio": a.precio, "imagen_url": a.imagen_url}
        for a in lista
    ]), 200

# ---------- POST endpoints (multipart/form-data / json) ----------
@app.route("/ropa", methods=["POST"])
def post_ropa():
    nombre = request.form.get("nombre")
    talla = request.form.get("talla")
    precio = parse_price(request.form.get("precio"))
    imagen = request.files.get("imagen")

    if not nombre:
        return jsonify({"error": "El campo nombre es obligatorio"}), 400

    imagen_url = None
    if imagen:
        saved = save_uploaded_file(imagen)
        if saved is None:
            return jsonify({"error": "Archivo de imagen inválido o extensión no permitida"}), 400
        imagen_url = saved

    nueva = Ropa(nombre=nombre, talla=talla, precio=precio, imagen_url=imagen_url)
    try:
        db.session.add(nueva)
        db.session.commit()
    except Exception as e:
        db.session.rollback()
        return jsonify({"error": "Error al guardar en la base de datos", "detail": str(e)}), 500

    return jsonify({
        "message": "Ropa agregada correctamente",
        "ropa": {"id": nueva.id, "nombre": nueva.nombre, "talla": nueva.talla, "precio": nueva.precio, "imagen_url": nueva.imagen_url}
    }), 201

@app.route("/carriolas", methods=["POST"])
def post_carriola():
    marca = request.form.get("marca")
    modelo = request.form.get("modelo")
    precio = parse_price(request.form.get("precio"))
    imagen = request.files.get("imagen")

    if not marca:
        return jsonify({"error": "El campo marca es obligatorio"}), 400

    imagen_url = None
    if imagen:
        saved = save_uploaded_file(imagen)
        if saved is None:
            return jsonify({"error": "Archivo de imagen inválido o extensión no permitida"}), 400
        imagen_url = saved

    nueva = Carriola(marca=marca, modelo=modelo, precio=precio, imagen_url=imagen_url)
    try:
        db.session.add(nueva)
        db.session.commit()
    except Exception as e:
        db.session.rollback()
        return jsonify({"error": "Error al guardar en la base de datos", "detail": str(e)}), 500

    return jsonify({
        "message": "Carriola agregada correctamente",
        "carriola": {"id": nueva.id, "marca": nueva.marca, "modelo": nueva.modelo, "precio": nueva.precio, "imagen_url": nueva.imagen_url}
    }), 201

@app.route("/accesorios", methods=["POST"])
def post_accesorio():
    # Acepta JSON o multipart/form-data con imagen
    if request.is_json:
        data = request.get_json()
        nombre = data.get("nombre")
        tipo = data.get("tipo")
        precio = parse_price(data.get("precio"))
        imagen_url = data.get("imagen_url")
    else:
        nombre = request.form.get("nombre")
        tipo = request.form.get("tipo")
        precio = parse_price(request.form.get("precio"))
        imagen = request.files.get("imagen")

        imagen_url = None
        if imagen:
            saved = save_uploaded_file(imagen)
            if saved is None:
                return jsonify({"error": "Archivo de imagen inválido o extensión no permitida"}), 400
            imagen_url = saved

    if not nombre:
        return jsonify({"error": "El campo nombre es obligatorio"}), 400

    nuevo = Accesorio(nombre=nombre, tipo=tipo, precio=precio, imagen_url=imagen_url)
    try:
        db.session.add(nuevo)
        db.session.commit()
    except Exception as e:
        db.session.rollback()
        return jsonify({"error": "Error al guardar en la base de datos", "detail": str(e)}), 500

    return jsonify({
        "message": "Accesorio agregado correctamente",
        "accesorio": {"id": nuevo.id, "nombre": nuevo.nombre, "tipo": nuevo.tipo, "precio": nuevo.precio, "imagen_url": nuevo.imagen_url}
    }), 201

# ---------- Single-item GETs ----------
@app.route("/ropa/<int:item_id>", methods=["GET"])
def get_ropa_by_id(item_id):
    r = Ropa.query.get_or_404(item_id)
    return jsonify({"id": r.id, "nombre": r.nombre, "talla": r.talla, "precio": r.precio, "imagen_url": r.imagen_url}), 200

@app.route("/carriolas/<int:item_id>", methods=["GET"])
def get_carriola_by_id(item_id):
    c = Carriola.query.get_or_404(item_id)
    return jsonify({"id": c.id, "marca": c.marca, "modelo": c.modelo, "precio": c.precio, "imagen_url": c.imagen_url}), 200

@app.route("/accesorios/<int:item_id>", methods=["GET"])
def get_accesorio_by_id(item_id):
    a = Accesorio.query.get_or_404(item_id)
    return jsonify({"id": a.id, "nombre": a.nombre, "tipo": a.tipo, "precio": a.precio, "imagen_url": a.imagen_url}), 200

# ---------- Health check ----------
@app.route("/health", methods=["GET"])
def health():
    return jsonify({"status": "ok"}), 200

# ---------- Error handlers ----------
@app.errorhandler(413)
@app.errorhandler(RequestEntityTooLarge)
def request_entity_too_large(error):
    return jsonify({"error": "Archivo demasiado grande (max 16MB)"}), 413

# ---------- Run ----------
if __name__ == "__main__":
    # Host 0.0.0.0 permite que otros dispositivos en la red se conecten (útil para pruebas en móvil)
    app.run(host="0.0.0.0", port=5000, debug=True)
