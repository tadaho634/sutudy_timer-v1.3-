from flask import Flask, jsonify, request

app = Flask(__name__)

# ユーザーのデータをシミュレーション
user_data = {
    "time_remaining": 25 * 60,
    "level": 1,
    "popcorn_count": 0
}

@app.route('/status', methods=['GET'])
def get_status():
    return jsonify(user_data)

@app.route('/update', methods=['POST'])
def update_status():
    data = request.json
    user_data["time_remaining"] = data.get("time_remaining", user_data["time_remaining"])
    user_data["level"] = data.get("level", user_data["level"])
    user_data["popcorn_count"] = data.get("popcorn_count", user_data["popcorn_count"])
    return jsonify({"message": "Data updated successfully", "data": user_data})

if __name__ == "__main__":
    app.run(debug=True)
