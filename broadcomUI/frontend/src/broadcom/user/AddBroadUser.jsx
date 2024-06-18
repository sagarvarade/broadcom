import React, { useState } from "react";

function AddBroadUser({ user, onSave, onDelete }) {
  const [formData, setFormData] = useState({
    name: user?.name || "",
    address: user?.address || "",
    phone: user?.phone || "",
    fbId: user?.fbId || "",
    whatsappId: user?.whatsappId || "",
    telegramId: user?.telegramId || "",
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSave(formData);
  };
  return (
    <form onSubmit={handleSubmit}>
      <div className="mb-3">
        <label className="form-label">Name</label>
        <input
          type="text"
          className="form-control"
          name="name"
          value={formData.name}
          onChange={handleChange}
          required
        />
      </div>
      <div className="mb-3">
        <label className="form-label">Address </label>
        <input
          type="text"
          className="form-control"
          name="name"
          value={formData.address}
          onChange={handleChange}
          required
        />
      </div>
      <div className="mb-3">
        <label className="form-label">Phone Number</label>
        <input
          type="text"
          className="form-control"
          name="phone"
          value={formData.phone}
          onChange={handleChange}
          required
        />
      </div>
      <div className="mb-3">
        <label className="form-label">Facebook ID</label>
        <input
          type="text"
          className="form-control"
          name="fbId"
          value={formData.fbId}
          onChange={handleChange}
        />
      </div>
      <div className="mb-3">
        <label className="form-label">WhatsApp ID</label>
        <input
          type="text"
          className="form-control"
          name="whatsappId"
          value={formData.whatsappId}
          onChange={handleChange}
        />
      </div>
      <div className="mb-3">
        <label className="form-label">Telegram ID</label>
        <input
          type="text"
          className="form-control"
          name="telegramId"
          value={formData.telegramId}
          onChange={handleChange}
        />
      </div>
      <button type="submit" className="btn btn-primary">
        Save
      </button>
      {user && (
        <button
          type="button"
          className="btn btn-danger ms-2"
          onClick={() => onDelete(user)}
        >
          Delete
        </button>
      )}
    </form>
  );
}

export default AddBroadUser;
