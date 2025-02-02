import React, { useState } from 'react';

function NewPolicyFormComponent() {
  const [policyType, setPolicyType] = useState('');
  const [formData, setFormData] = useState({ policyName: '', policyDetails: '' });
  const [submitted, setSubmitted] = useState(false);

  const handleInputChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Validate and submit form data
    setSubmitted(true);
  };

  if (submitted) {
    return <div>Acknowledgement: Policy submitted successfully!</div>;
  }

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Policy Name:</label>
        <input type="text" name="policyName" value={formData.policyName} onChange={handleInputChange} required />
      </div>
      <div>
        <label>Policy Details:</label>
        <textarea name="policyDetails" value={formData.policyDetails} onChange={handleInputChange} required />
      </div>
      <div>
        <label>Policy Type:</label>
        <select value={policyType} onChange={(e) => setPolicyType(e.target.value)} required>
          <option value="">Select Policy Type</option>
          <option value="life">Life</option>
          <option value="health">Health</option>
          <option value="vehicle">Vehicle</option>
          <option value="travel">Travel</option>
          <option value="child-plan">Chid-plan</option>
        </select>
      </div>
      <button type="submit">Submit</button>
    </form>
  );
}

export default NewPolicyFormComponent;

